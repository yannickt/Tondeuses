package me.tailliez.tondeuse.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProgramTest {

    @Test
    void getMove() {
        Program p = new Program("T1", new Position(1, 1, Direction.North), "AGD");
        Assertions.assertEquals(Move.Forward, p.getMove(0));
        Assertions.assertEquals(Move.Left, p.getMove(1));
        Assertions.assertEquals(Move.Right, p.getMove(2));
        Exception e1 = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> p.getMove(-1));
        Assertions.assertEquals("Position invalide dans le flux du programme.", e1.getMessage());
        Exception e2 = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> p.getMove(3));
        Assertions.assertEquals("Fin du programme atteinte.", e2.getMessage());
    }

    @Test
    void isEndReached() {
        Program p = new Program("T1", new Position(1, 1, Direction.North), "AGD");
        Assertions.assertFalse(p.isEndReached(0));
        Assertions.assertFalse(p.isEndReached(1));
        Assertions.assertFalse(p.isEndReached(2));
        Assertions.assertTrue(p.isEndReached(3));
    }
}