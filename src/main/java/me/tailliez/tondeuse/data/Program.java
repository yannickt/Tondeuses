package me.tailliez.tondeuse.data;

/** Programme de déplacement d'une tondeuse */
public class Program {

    private String name;
    private Position initialPosition;
    private String moves;

    public Program(String name, Position initialPosition, String moves) {
        this.name = name;
        this.initialPosition = initialPosition;
        this.moves = moves;
    }

    public String getMoves() {
        return moves;
    }

    public Move getMove(int step) {
        if ( step < 0) {
            throw new IndexOutOfBoundsException("Position invalide dans le flux du programme.");
        }
        if ( moves.length() <= step ) {
            throw new IndexOutOfBoundsException("Fin du programme atteinte.");
        }
        return Move.getMove(moves.charAt(step));
    }

    public boolean isEndReached(int step) {
        return step >= moves.length();
    }

    public Position getInitialPosition() {
        return this.initialPosition;
    }

    public String getName() {
        return this.name;
    }

}
