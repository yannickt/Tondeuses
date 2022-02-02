package me.tailliez.tondeuse.execution;

import me.tailliez.tondeuse.data.Direction;
import me.tailliez.tondeuse.data.Place;
import me.tailliez.tondeuse.data.Position;
import me.tailliez.tondeuse.data.Program;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

class ExecutorTest {

    @Test
    public void execute_1_task() {
        Program p = new Program("Tondeuse N°1", new Position(new Place(1, 2), Direction.North), "GAGAGAGAA");
        ExecutionContext context = new ExecutionContext(new Place(5, 5), p);
        Executor e = new Executor(Collections.singletonList(context));
        e.execute();

        Assertions.assertEquals(new Place(1, 3), context.getLastPosition().getPlace());
        Assertions.assertEquals(Direction.North, context.getLastPosition().getDirection());
        Assertions.assertFalse(context.isAborted());
    }

    @Test
    public void execute_2_task_with_different_length() {
        Program p1 = new Program("Tondeuse N°1", new Position(new Place(1, 2), Direction.North), "GAGAGAGAA");
        ExecutionContext context1 = new ExecutionContext(new Place(5, 5), p1);

        Program p2 = new Program("Tondeuse N°2", new Position(new Place(3, 3), Direction.East), "AADAADADDA");
        ExecutionContext context2 = new ExecutionContext(new Place(5, 5), p2);

        Executor e = new Executor(Arrays.asList(context1, context2));
        e.execute();

        Assertions.assertEquals(new Place(1, 3), context1.getLastPosition().getPlace());
        Assertions.assertEquals(Direction.North, context1.getLastPosition().getDirection());
        Assertions.assertNull(context1.getError());
        Assertions.assertFalse(context1.isAborted());
        Assertions.assertEquals(new Place(5, 1), context2.getLastPosition().getPlace());
        Assertions.assertEquals(Direction.East, context2.getLastPosition().getDirection());
        Assertions.assertNull(context2.getError());
        Assertions.assertFalse(context2.isAborted());
    }

    @Test
    public void execute_4_task_with_exit_different_length() {
        ExecutionContext context1 = new ExecutionContext(new Place(4, 4), new Program("Tondeuse N°1", new Position(new Place(1, 1), Direction.North), "AAAAA"));
        ExecutionContext context2 = new ExecutionContext(new Place(4, 4), new Program("Tondeuse N°2", new Position(new Place(1, 1), Direction.East), "AAAAA"));
        ExecutionContext context3 = new ExecutionContext(new Place(4, 4), new Program("T3", new Position(new Place(1, 1), Direction.South), "AAAAA"));
        ExecutionContext context4 = new ExecutionContext(new Place(4, 4), new Program("T4", new Position(new Place(1, 1), Direction.West), "AAAAA"));
        Executor e = new Executor(Arrays.asList(context1, context2, context3, context4));
        e.execute();

        Assertions.assertTrue(context1.isAborted());
        Assertions.assertEquals(3, context1.getStep());
        Assertions.assertTrue(context2.isAborted());
        Assertions.assertEquals(3, context2.getStep());
        Assertions.assertTrue(context3.isAborted());
        Assertions.assertEquals(1, context3.getStep());
        Assertions.assertTrue(context4.isAborted());
        Assertions.assertEquals(1, context4.getStep());
    }

    @Test
    public void execute_4_task_with_collision() {
        ExecutionContext context1 = new ExecutionContext(new Place(5, 5), new Program("Tondeuse N°1", new Position(new Place(2, 2), Direction.North), "GGGG"));
        ExecutionContext context2 = new ExecutionContext(new Place(5, 5), new Program("Tondeuse N°2", new Position(new Place(1, 1), Direction.North), "ADAA"));
        Executor e = new Executor(Arrays.asList(context1, context2));
        e.execute();
        e.showResults();

        Assertions.assertEquals(new Place(2, 2), context1.getLastPosition().getPlace());
        Assertions.assertEquals(Direction.North, context1.getLastPosition().getDirection());
        Assertions.assertFalse(context1.isAborted());
        Assertions.assertNull(context1.getError());
        Assertions.assertTrue(context2.isAborted());
        Assertions.assertEquals("Durant l'éxécution du déplacement n°3, une collision a eue lieue avec Tondeuse N°1.",context2.getError());
    }

}