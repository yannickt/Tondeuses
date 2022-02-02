package me.tailliez.tondeuse.execution;

import me.tailliez.tondeuse.data.Place;
import me.tailliez.tondeuse.data.Position;
import me.tailliez.tondeuse.data.Program;

/** Contexte d'execution du programme d'une tondeuse */
public class ExecutionContext {

    private Place limit;

    private int step = 0;
    private Position lastPosition;
    private boolean aborted = false;
    private String error = null;

    private Program program;

    public ExecutionContext(Place limit, Program program) {
        this.limit = limit;
        this.lastPosition = program.getInitialPosition().clone();
        this.program = program;
    }

    public int getStep() {
        return step;
    }

    void incrementeStep() {
        this.step++;
    }

    public Position getLastPosition() {
        return lastPosition;
    }

    void setLastPosition(Position lastPosition) {
        this.lastPosition = lastPosition;
    }

    public Program getProgram() {
        return program;
    }

    public Place getLimit() {
        return limit;
    }

    public boolean isAborted() {
        return aborted;
    }

    void setAborted(boolean aborted) {
        this.aborted = aborted;
    }

    public boolean isEndReached() {
        return this.step >= this.getProgram().getMoves().length();
    }

    public String getError() {
        return error;
    }

    void setError(String error) {
        this.error = error;
    }
}