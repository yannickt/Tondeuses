package me.tailliez.tondeuse.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MoveTest {

    @Test
    public void is_all_value_ok() {
        Assertions.assertEquals(3, Move.values().length);
        Assertions.assertEquals(Move.Left.getCode(), 'G');
        Assertions.assertEquals(Move.Right.getCode(), 'D');
        Assertions.assertEquals(Move.Forward.getCode(), 'A');
    }

    @Test
    public void getDirection_ok() {
        Assertions.assertEquals(Move.Left, Move.getMove('G'));
        Assertions.assertEquals(Move.Right, Move.getMove('D'));
        Assertions.assertEquals(Move.Forward, Move.getMove('A'));
    }

    @Test
    public void getDirection_unknow() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Move.getMove('!'));
    }


}