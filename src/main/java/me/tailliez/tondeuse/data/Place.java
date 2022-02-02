package me.tailliez.tondeuse.data;

/**
 * Représente un emplacement, de coordonnée (x,y).
 */
public record Place(int x, int y) {

    @Override
    public String toString() {
        return x + " " + y;
    }

    public boolean isInBound(Place bound) {
        return 0 <= x && x <= bound.x
                && 0 <= y && y <= bound.y;
    }
}
