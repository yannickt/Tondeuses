package me.tailliez.tondeuse.data;

import java.util.Objects;

/**
 * Position d'une tondeuse : Son emplacement et sa direction.
 */
public class Position {

    private Place place;
    private Direction direction;

    public Position(int x, int y, Direction direction) {
        this.place = new Place(x, y);
        this.direction = direction;
    }

    public Position(Place place, Direction direction) {
        this.place = place;
        this.direction = direction;
    }

    @Override
    public Position clone() {
        return new Position(this.place, this.direction);
    }

    @Override
    public String toString() {
        return place.toString() + " " + direction.getCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(place, position.place) && direction == position.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(place, direction);
    }

    /**
     * @return Retourne <code>true</code> si la tondeuse en paramètre se situe au même emplacement, quelquesoit sa direction.
     */
    public boolean isSamePlace(Position p) {
        return this.place.equals(p.place);
    }

    public void turnLeft() {
        switch (direction) {
            case North:
                direction = Direction.West;
                break;
            case East:
                direction = Direction.North;
                break;
            case South:
                direction = Direction.East;
                break;
            case West:
                direction = Direction.South;
                break;
        }
    }

    public void turnRight() {
        switch (direction) {
            case North:
                direction = Direction.East;
                break;
            case East:
                direction = Direction.South;
                break;
            case South:
                direction = Direction.West;
                break;
            case West:
                direction = Direction.North;
                break;
        }
    }

    public void forward() {
        switch (direction) {
            case North:
                place = new Place(place.x(), place.y() + 1);
                break;
            case East:
                place = new Place(place.x() + 1, place.y());
                break;
            case South:
                place = new Place(place.x(), place.y() - 1);
                break;
            case West:
                place = new Place(place.x() - 1, place.y());
                break;
        }
    }

    public Place getPlace() {
        return place;
    }

    public Direction getDirection() {
        return direction;
    }

}
