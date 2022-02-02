package me.tailliez.tondeuse.execution;

import me.tailliez.tondeuse.data.Move;
import me.tailliez.tondeuse.data.Position;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


public class Executor {

    private List<ExecutionContext> contexts;

    public Executor(List<ExecutionContext> contexts) {
        if (contexts == null) {
            contexts = new ArrayList<>();
        }
        this.contexts = contexts;
    }

    /**
     * Execute en parallèle les programmes de toutes les tondeuses
     */
    public void execute() {
        // Execute pas à pas.
        List<ExecutionContext> runningContexts = new ArrayList<>(contexts);

        int step = 1;
        while (!runningContexts.isEmpty()) {
            // Pour chaque contexte à executer, lance une étape
            for (Iterator<ExecutionContext> i = runningContexts.iterator(); i.hasNext(); ) {
                ExecutionContext context = i.next();
                try {
                    executeNextStep(context);
                    // Stoppe l'execution de programme terminé (sort le contexte)
                    if (context.isEndReached()) {
                        i.remove();
                    }
                } catch (ExecutionException e) {
                    // Stoppe l'execution de programme en erreur (sort le contexte)
                    i.remove();
                }
            }
        }
    }

    public void showResults() {
        System.out.println("Résultats des executions :");
        for (ExecutionContext context : contexts) {
            System.out.println(" - " + context.getProgram().getName() + " : ");
            if (context.isAborted()) {
                System.out.println("   Le programme a rencontré un problème à l'étape " + (context.getStep()+1) + " : " + context.getError());
            }
            if (context.isEndReached()) {
                System.out.println("   Le programme s'est déroulé jusqu'au bout.");
            }
            System.out.println("   Position finale : " + context.getLastPosition().toString());
        }
    }

    private void executeNextStep(ExecutionContext context) throws ExecutionException {
        try {
            if (context.isEndReached() || context.isAborted()) return;

            Position position = context.getLastPosition();
            Move move = context.getProgram().getMove(context.getStep());

            switch (move) {
                case Left:
                    position.turnLeft();
                    break;
                case Right:
                    position.turnRight();
                    break;
                case Forward:
                    Position newPosition = position.clone();
                    newPosition.forward();

                    checkIfOutOfBound(context, newPosition);
                    checkIfCollision(context, newPosition);

                    context.setLastPosition(newPosition);
                    break;
            }
            context.incrementeStep();

        } catch (ExecutionException e) {
            context.setAborted(true);
            context.setError(e.getMessage());
            throw e;
        } catch (Exception e) {
            String error = "Erreur inattendue : " + e.getMessage();
            context.setAborted(true);
            context.setError(error);
            throw new ExecutionException(error, e);
        }
    }

    /**
     * Vérifie si la tondeuse reste dans son périmètre.
     */
    private void checkIfOutOfBound(ExecutionContext context, Position newPosition) throws ExecutionException {
        if (!newPosition.getPlace().isInBound(context.getLimit())) {
            throw new ExecutionException("Dépasse la limite de la pelouse.");
        }
    }

    /**
     * Vérifie si la tondeuse n'entre pas en collision avec une autre.
     */
    private void checkIfCollision(ExecutionContext context, Position newPosition) throws ExecutionException {
        Optional<ExecutionContext> collision = contexts.stream()
                .filter(c -> c.getLastPosition().isSamePlace(newPosition))
                .filter(c -> !c.getProgram().getName().equals(context.getProgram().getName()))
                .findAny();
        if (collision.isPresent()) {
            throw new ExecutionException("Durant l'éxécution du déplacement n°" + (context.getStep() + 1) + ", une collision a eue lieue avec " + collision.get().getProgram().getName() + ".");
        }
    }

}
