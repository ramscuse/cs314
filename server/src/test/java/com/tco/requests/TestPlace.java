package com.tco.requests;

import static java.lang.Math.PI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPlace {
    
    static final Double piHalf = (PI / 2);

    @Test
    @DisplayName("arackow: place is at 0N,0E")
    public void testType() {
        Place testPlace = new Place("0.", "0.");
        Double testLatitude = testPlace.latRadians();
        Double testLongitude = testPlace.lonRadians();
        assertEquals(0., testLatitude);
        assertEquals(0., testLongitude);
    }

    @Test
    @DisplayName("arackow: place is at 90N,0E (magnetic North Pole")
    public void testNorthPole() {
        Place testPlace = new Place("90.", "0.");
        Double testLatitude = testPlace.latRadians();
        Double testLongitude = testPlace.lonRadians();
        assertEquals(piHalf, testLatitude);
        assertEquals(0., testLongitude);
    }

    
    @Test
    @DisplayName("zplamb: North Eastern Hemisphere Test")
    public void testNEHemi() {
        Place testPlace = new Place("47.", "9.");
        Double testLatitude = testPlace.latRadians();
        Double testLongitude = testPlace.lonRadians();
        assertEquals(0.8203, testLatitude, .01);
        assertEquals(0.1571, testLongitude, .01);
    }

    @Test
    @DisplayName("zplamb: North Western Hemisphere Test")
    public void testNWHemi() {
        Place testPlace = new Place("37.", "-108.");
        Double testLatitude = testPlace.latRadians();
        Double testLongitude = testPlace.lonRadians();
        assertEquals(0.6458, testLatitude, .01);
        assertEquals(-1.8849, testLongitude, .01);
    }

    @Test
    @DisplayName("zplamb: South Eastern Hemisphere Test")
    public void testSEHemi() {
        Place testPlace = new Place("-24.", "135.");
        Double testLatitude = testPlace.latRadians();
        Double testLongitude = testPlace.lonRadians();
        assertEquals(-0.4188, testLatitude, .01);
        assertEquals(2.3561, testLongitude, .01);
    }

    @Test
    @DisplayName("zplamb: South Western Hemisphere Test")
    public void testSWHemi() {
        Place testPlace = new Place("-26.", "-61.");
        Double testLatitude = testPlace.latRadians();
        Double testLongitude = testPlace.lonRadians();
        assertEquals(-0.4537, testLatitude, .01);
        assertEquals(-1.0647, testLongitude, .01);
    }

    @Test
    @DisplayName("ramscuse: Add (0,180) test case")
    public void test180() {
        Place testPlace = new Place("0.", "180.");
        Double testLatitude = testPlace.latRadians();
        Double testLongitude = testPlace.lonRadians();
        assertEquals(0, testLatitude, .01);
        assertEquals(3.14159, testLongitude, .01);
    }
    
    @Test
    @DisplayName("ramscuse: Add (0,-180) test case")
    public void testn180() {
        Place testPlace = new Place("0.", "-180.");
        Double testLatitude = testPlace.latRadians();
        Double testLongitude = testPlace.lonRadians();
        assertEquals(0, testLatitude, .01);
        assertEquals(-3.14159, testLongitude, .01);
    }

    @Test
    @DisplayName("stchambe: sanity test (0,180) equals (0,-180)")
    public void samePlaceOnEquator(){
        Place testPlace = new Place("0.0", "180.0");
        Place testPlace2 = new Place("0.0","-180.0");
        Double testLat1 = testPlace.latRadians();
        Double testLat2 = testPlace2.latRadians();
        assertEquals(testLat1, testLat2);
        Double testLon1 = testPlace.lonRadians();
        Double testLon2 = testPlace2.lonRadians();
        if(testLon1 == (-1)*testLon2){
            assertEquals(testLon1, testLon2*-1);
        }
        
    }
}
