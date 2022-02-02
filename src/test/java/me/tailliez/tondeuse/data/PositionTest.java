package me.tailliez.tondeuse.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    void testToString() {
        Position p = new Position(new Place(1, 2), Direction.North);

        Assertions.assertEquals("1 2 N", p.toString());
    }

    @Test
    void isSamePlace() {
        Position p1n = new Position(1, 2, Direction.North);
        Position p1s = new Position(1, 2, Direction.South);
        Position p2n = new Position(2, 1, Direction.North);
        Position p2s = new Position(2, 1, Direction.South);

        Assertions.assertTrue(p1n.isSamePlace(p1n));
        Assertions.assertTrue(p1n.isSamePlace(p1s));
        Assertions.assertFalse(p1n.isSamePlace(p2n));
        Assertions.assertFalse(p1n.isSamePlace(p2s));
    }

    @Test
    void turnLeft() {
        Position p1 = new Position(1, 2, Direction.North);
        Position p1n = new Position(1, 2, Direction.North);
        Position p1e = new Position(1, 2, Direction.East);
        Position p1s = new Position(1, 2, Direction.South);
        Position p1w = new Position(1, 2, Direction.West);

        p1.turnLeft();
        Assertions.assertEquals(p1w, p1);
        p1.turnLeft();
        Assertions.assertEquals(p1s, p1);
        p1.turnLeft();
        Assertions.assertEquals(p1e, p1);
        p1.turnLeft();
        Assertions.assertEquals(p1n, p1);
    }

    @Test
    void turnRight() {
        Position p1 = new Position(1, 2, Direction.North);
        Position p1n = new Position(1, 2, Direction.North);
        Position p1e = new Position(1, 2, Direction.East);
        Position p1s = new Position(1, 2, Direction.South);
        Position p1w = new Position(1, 2, Direction.West);

        p1.turnRight();
        Assertions.assertEquals(p1e, p1);

        p1.turnRight();
        Assertions.assertEquals(p1s, p1);

        p1.turnRight();
        Assertions.assertEquals(p1w, p1);

        p1.turnRight();
        Assertions.assertEquals(p1n, p1);
    }

    @Test
    void forward() {
        Position p1n = new Position(1, 2, Direction.North);
        p1n.forward();
        Assertions.assertEquals(new Position(1, 3, Direction.North), p1n);

        Position p1e = new Position(1, 2, Direction.East);
        p1e.forward();
        Assertions.assertEquals(new Position(2, 2, Direction.East), p1e);

        Position p1s = new Position(1, 2, Direction.South);
        p1s.forward();
        Assertions.assertEquals(new Position(1, 1, Direction.South), p1s);

        Position p1w = new Position(1, 2, Direction.West);
        p1w.forward();
        Assertions.assertEquals(new Position(0, 2, Direction.West), p1w);
    }
}