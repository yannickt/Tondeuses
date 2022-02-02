package me.tailliez.tondeuse.data;

/**
 * Déplacement de la tondeuse
 */
public enum Move {
    Left('G'),
    Right('D'),
    Forward('A');

    private char code;

    Move(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }

    public static Move getMove(char aCode) {
        for (Move m : Move.values()) {
            if (m.code == aCode) {
                return m;
            }
        }
        throw new IllegalArgumentException("Movement \"" + aCode + "\" inconnu");
    }
}
