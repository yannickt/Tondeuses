package me.tailliez.tondeuse.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    public void is_all_value_ok() {
        Assertions.assertEquals(4, Direction.values().length);
        Assertions.assertEquals(Direction.North.getCode(), 'N');
        Assertions.assertEquals(Direction.East.getCode(), 'E');
        Assertions.assertEquals(Direction.South.getCode(), 'S');
        Assertions.assertEquals(Direction.West.getCode(), 'W');
    }

    @Test
    public void getDirection_ok() {
        Assertions.assertEquals(Direction.North, Direction.getDirection('N'));
        Assertions.assertEquals(Direction.East, Direction.getDirection('E'));
        Assertions.assertEquals(Direction.South, Direction.getDirection('S'));
        Assertions.assertEquals(Direction.West, Direction.getDirection('W'));
    }

    @Test
    public void getDirection_unknow() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Direction.getDirection('!'));
    }


}