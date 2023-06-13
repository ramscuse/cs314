package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPlaces {
    private Places testPlaces;

    @Test
    @DisplayName("arackow: Base case (no places)")
    public void testType() {
        Places testPlaces = new Places();
        assertEquals(0, testPlaces.size());
    }

    @Test
    @DisplayName("ramscuse: Add single place")
    public void testOnePlace() {
        Places testPlaces = new Places();
        Place testPlace = new Place("0.","0.");
        testPlaces.add(testPlace);
        assertEquals(1, testPlaces.size());
    }

    @Test
    @DisplayName("ramscuse: Add five places")
    public void testFivePlace() {
        Places testPlaces = new Places();

        testPlaces.add(new Place("0.","0."));
        testPlaces.add(new Place("10.","10."));
        testPlaces.add(new Place("-10.","10."));
        testPlaces.add(new Place("10.","-10."));
        testPlaces.add(new Place("-10.","-10."));

        assertEquals(5, testPlaces.size());
    }
}
