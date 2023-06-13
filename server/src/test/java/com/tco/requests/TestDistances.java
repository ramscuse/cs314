package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDistances {
    @Test
    @DisplayName("zplamb: 0 Distance test")
    public void testNoDistances() {
        Distances distances = new Distances();
        assertEquals(0, distances.size());
        assertEquals(0L, distances.total());
    }

    @Test
    @DisplayName("arackow: 1 Distance test")
    public void testOneDistance() {
        Distances distances = new Distances();
        distances.add(10L);
        assertEquals(1, distances.size());
        assertEquals(10L, distances.total());
    }

    @Test
    @DisplayName("arackow: 5 Distance test")
    public void testFiveDistances() {
        Distances distances = new Distances();
        distances.add(10L);
        distances.add(100L);
        distances.add(1000L);
        distances.add(10000L);
        distances.add(100000L);
        assertEquals(5, distances.size());
        assertEquals(111110L, distances.total());
    }
}