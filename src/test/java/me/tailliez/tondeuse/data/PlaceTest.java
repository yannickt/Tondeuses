package me.tailliez.tondeuse.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaceTest {

    @Test
    void testToString() {
        Place p = new Place(4,5);
        Assertions.assertEquals("4 5", p.toString());
    }

    @Test
    void isInBound() {
        Place limit = new Place( 5,5);
        Assertions.assertTrue(new Place(0,0).isInBound(limit));
        Assertions.assertTrue(new Place(2,0).isInBound(limit));
        Assertions.assertTrue(new Place(0,2).isInBound(limit));
        Assertions.assertTrue(new Place(2,2).isInBound(limit));
        Assertions.assertTrue(new Place(2,5).isInBound(limit));
        Assertions.assertTrue(new Place(5,2).isInBound(limit));
        Assertions.assertTrue(new Place(5,5).isInBound(limit));
        Assertions.assertFalse(new Place(-1,-1).isInBound(limit));
        Assertions.assertFalse(new Place(-1,0).isInBound(limit));
        Assertions.assertFalse(new Place(0,-1).isInBound(limit));
        Assertions.assertFalse(new Place(5,6).isInBound(limit));
        Assertions.assertFalse(new Place(6,5).isInBound(limit));
        Assertions.assertFalse(new Place(6,6).isInBound(limit));
    }

}