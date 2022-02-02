package me.tailliez.tondeuse;

import me.tailliez.tondeuse.data.Program;
import me.tailliez.tondeuse.execution.ExecutionContext;
import me.tailliez.tondeuse.execution.Executor;
import me.tailliez.tondeuse.reader.MovementsFileReader;

import java.util.ArrayList;
import java.util.List;

public class Launcher {

    public static void main(String... args) {

        try {

            if (args.length != 1) {
                throw new IllegalArgumentException("Il faut un nom de fichier en paramètre");
            }

            MovementsFileReader reader = new MovementsFileReader(args[0], (index) -> "Tondeuse n°" + index);
            List<ExecutionContext> contexts = reader.read();

            Executor executor = new Executor(contexts);
            executor.execute();
            executor.showResults();

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
