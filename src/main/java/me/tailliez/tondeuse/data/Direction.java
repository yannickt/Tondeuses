package me.tailliez.tondeuse.data;

/**
 * Direction (absolue) de la tondeuse
 */
public enum Direction {
    North('N'),
    East('E'),
    South('S'),
    West('W');

    private final char code;

    Direction(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }

    public static Direction getDirection(char aCode) {
        for (Direction d : Direction.values()) {
            if (d.code == aCode) {
                return d;
            }
        }
        throw new IllegalArgumentException("Direction \"" + aCode + "\" inconnue");
    }

}