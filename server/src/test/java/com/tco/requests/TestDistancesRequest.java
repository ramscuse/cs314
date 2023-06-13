package com.tco.requests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestDistancesRequest {
    
    DistancesRequest request;
    Places places;
    Distances distances;

    static final long bigRadius = 1000000L;
    static final long bigPi = Math.round(Math.PI * bigRadius);
    static final long bigPiHalf = Math.round(Math.PI / 2 * bigRadius);

    @BeforeEach
    public void beforeEach() {
        places = new Places();
        request = new DistancesRequest(bigRadius, places);
    }

    @Test
    @DisplayName("zplamb: empty places")
    public void testEmptyPlaces() {
        request = new DistancesRequest(bigRadius, places);
        request.buildResponse();

        distances = request.distances();
        assertEquals(0, distances.size());
        assertEquals(0L, distances.total());

        assertEquals(0, request.places().size());
        assertEquals(bigRadius, request.earthRadius());
    }

    @Test
    @DisplayName("arackow: one place") //one place - distance is zero
    public void testOnePlace() {
        Place testPlace = new Place("0.0", "0.0");
        //places.addPlace(testPlace);
        places.add(testPlace);
        assertEquals(1,places.size());

        request = new DistancesRequest(bigRadius, places);
        request.buildResponse();

        distances = request.distances();
        assertEquals(1, distances.size());
        assertEquals(0L, distances.total());
        assertEquals(1, request.places().size());
        assertEquals(bigRadius, request.earthRadius());
    }

    @Test
    @DisplayName("stchambe: five places test")
    public void testFivePlace(){
        Place p0 = new Place("0.0", "0.0");
        Place p1 = new Place("1.0", "1.0");
        Place p2 = new Place("2.0", "2.0");
        Place p3 = new Place("3.0", "3.0");
        Place p4 = new Place("4.0", "4.0");
        places.add(p0);
        places.add(p1);
        places.add(p2);
        places.add(p3);
        places.add(p4);
        assertEquals(5, places.size());
        request = new DistancesRequest(3959.0, places);
        request.buildResponse();
    }
}
