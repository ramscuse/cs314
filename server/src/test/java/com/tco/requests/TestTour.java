package com.tco.requests;

import com.tco.requests.Places;
import com.tco.requests.Place;
import com.tco.requests.DistancesRequest;
import com.tco.requests.TourRequest;

import java.util.HashMap;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestTour {
    private static final transient Logger log = LoggerFactory.getLogger(TourRequest.class);
    
    private TourRequest currentTour;
    Places places;
    Place place1;
    Place place2;
    Place place3;
    
    String latStr;
    String lonStr;

    @BeforeEach
    public void ConfigTestCases() {
        place1 = new Place(latStr, lonStr);
        place2 = new Place(latStr, lonStr);
        place3 = new Place(latStr, lonStr);
        place1.put("distance", "55");
        place2.put("distance", "30");
        place3.put("distance", "15");
        
        places = new Places();
        places.add(place1);
        places.add(place2);
        places.add(place3);

        currentTour = new TourRequest(3959.0, 0.0, places);

        //currentTour.append(currentTour.getIndexOfValue("15", places));
    }

    @Test
    @DisplayName("klazare: Test size() in Tour ")
    public void testAppend() {
        assertTrue(currentTour.getOptimizedTour().size() == 3);
    }

    @Test
    @DisplayName("klazare: Test currentTourElement in Tour ")
    public void testcurrentTourDistance() {
        int tourElement = 0;
        //assertTrue(currentTour.getcurrentTourDistance() == 1);
    }

    @Test
    @DisplayName("klazare: Test Tour getValue ")
    public void testTourGetValue() {
        int tourElement = 0;
        //assertTrue(currentTour.getValue(tourElement) == 2);
    }

    @Test
    @DisplayName("klazare: Test convertTourToPlaces ")
    public void testConvertTourToPlaces() {
       // Places result = currentTour.convertTourToPlaces();
        //assertTrue(Tour.getIndexOfValue("15", result) == 0);
    }

    @Test
    @DisplayName("klazare: Test getPlace() ")
    public void testGetPlace() {
        Place newPlace = currentTour.getPlace(0);
        String dist = newPlace.get("distance");
        //assertTrue(dist == "15");
    }

    @Test
    @DisplayName("klazare: Test setValue() ")
    public void testSetValue() {
        int index = 1;
        //currentTour.setValue(index, 5);
        //assertTrue(currentTour.getValue(1) == 5);
    }

    @Test
    @DisplayName("stchambe: Test response equals 0 TourRequest")
    public void testZeroResponse(){
        Places testerPlaces = new Places();
        Place fartLand = new Place("0.0", "0.0");
        testerPlaces.add(fartLand);
        TourRequest tr = new TourRequest(6378.0, 0.0, testerPlaces);
        assertEquals(testerPlaces, tr.getOptimizedTour());

    }

    @Test
    @DisplayName("stchambe: Test Non-zero response TourRequest")
    public void testNonZeroResponse(){
        Places testPlaces = new Places();
        Place p1 = new Place("40.549242","-105.091782");
        Place p2 = new Place("40.565910","-104.674301");
        Place p3 = new Place("40.313484","-105.273743");
        Place p4 = new Place("40.286074","-104.223175");
        testPlaces.add(p1);
        testPlaces.add(p2);
        testPlaces.add(p3);
        testPlaces.add(p4);
        TourRequest tr = new TourRequest(3959.0,1.0,testPlaces);
        tr.buildResponse();
        Places testerOptimizedTour = new Places();
        testerOptimizedTour.add(p1);
        testerOptimizedTour.add(p3);
        testerOptimizedTour.add(p2);
        testerOptimizedTour.add(p4);
        assertEquals(tr.getOptimizedTour(), testerOptimizedTour);
    }

    @Test
    @DisplayName("stchambe: Bowtie Tour test")
    public void bowtieTest(){
        Places testerPlaces = new Places();
        Place arizona = new Place("35","-110");
        Place iowa = new Place("41", "-92");
        Place arkansas = new Place("35", "-92");
        Place wyoming = new Place("41","-110");
        testerPlaces.add(arizona);
        testerPlaces.add(iowa);
        testerPlaces.add(arkansas);
        testerPlaces.add(wyoming);
        DistancesRequest dr = new DistancesRequest(3959.0, testerPlaces);
        dr.buildResponse();
        Distances tripBefore = dr.distances();
        Long tripLengthBefore = tripBefore.total();
        TourRequest tr = new TourRequest(3959.0, 0.01, testerPlaces);
        tr.buildResponse();
        Places testerOptimizedPlaces = new Places();
        testerOptimizedPlaces.add(arizona);
        testerOptimizedPlaces.add(wyoming);
        testerOptimizedPlaces.add(iowa);
        testerOptimizedPlaces.add(arkansas);
        assertEquals(testerOptimizedPlaces,tr.getOptimizedTour());
        DistancesRequest nextDR = new DistancesRequest(3959.0, tr.getOptimizedTour());
        nextDR.buildResponse();
        Distances tripOptimized = nextDR.distances();
        Long optimizedTotal = tripOptimized.total();
        log.info("Samuel Bowtie distances before optimization: {}", tripLengthBefore);
        log.info("Samuel Bowtie distances after optimization: {}", optimizedTotal);
        


    }
    
    @Test
    @DisplayName("brauerbc: Test shorter trip")
    public void shortertriptest(){
        Places inputPlaces = new Places();
        Place Idaho = new Place("44","-116");
        Place Pennsylvania = new Place("40","-88");
        Place Alabama = new Place("32","-88");
        Place NewMexico = new Place("31","-108");
        Place Minnesota = new Place("47","-96");
        Place Georgia = new Place("32","-82");
        Place California = new Place("35","-117");

        inputPlaces.add(Idaho);
        inputPlaces.add(Pennsylvania);
        inputPlaces.add(Alabama);
        inputPlaces.add(NewMexico);
        inputPlaces.add(Minnesota);
        inputPlaces.add(Georgia);
        inputPlaces.add(California);

        DistancesRequest dr = new DistancesRequest(3959.0, inputPlaces);
        dr.buildResponse();
        Long beforeLength = dr.distances().total();

        TourRequest tr = new TourRequest(3959.0, 1.0, inputPlaces);
        tr.buildResponse();

        DistancesRequest optDr = new DistancesRequest(3959.0, tr.getOptimizedTour());
        optDr.buildResponse();
        Distances tripOptimized = optDr.distances();
        Long afterLength = tripOptimized.total();
        
        log.info("Braidon: test optimized trip is shorter\nBefore length: {}", beforeLength);
        log.info("After length: {}", afterLength);

        assertEquals(true, (afterLength < beforeLength));
    }

    @Test
    @DisplayName("brauerbc: Test 2 places")
    public void twoplacestest(){
        Places twoPlaces = new Places();
        Place Idaho = new Place("44","-116");
        Place Pennsylvania = new Place("40","-88");

        twoPlaces.add(Idaho);
        twoPlaces.add(Pennsylvania);

        DistancesRequest dr = new DistancesRequest(3959.0, twoPlaces);
        dr.buildResponse();

        TourRequest tr = new TourRequest(3959.0, 1.0, twoPlaces);
        tr.buildResponse();

        log.info("Braidon testtwoplaces, dr: {}", dr.places());
        log.info("Braidon testtwoplaces, tr: {}", tr.getOptimizedTour());

        assertEquals(dr.places(), tr.getOptimizedTour());
    }

    @Test
    @DisplayName("brauerbc: Test 3 places")
    public void threeplacestest(){
        Places twoPlaces = new Places();
        Place Idaho = new Place("44","-116");
        Place Pennsylvania = new Place("40","-88");
        Place Alabama = new Place("32","-88");

        twoPlaces.add(Idaho);
        twoPlaces.add(Pennsylvania);
        twoPlaces.add(Alabama);

        DistancesRequest dr = new DistancesRequest(3959.0, twoPlaces);
        dr.buildResponse();
        Long beforeLength = dr.distances().total();

        TourRequest tr = new TourRequest(3959.0, 1.0, twoPlaces);
        tr.buildResponse();

        DistancesRequest optDr = new DistancesRequest(3959.0, tr.getOptimizedTour());
        optDr.buildResponse();
        Distances tripOptimized = optDr.distances();
        Long afterLength = tripOptimized.total();

        log.info("Braidon testthreeplaces, before length: {}", beforeLength);
        log.info("Braidon testthreeplaces, after length: {}", afterLength);

        assertEquals(true, afterLength <= beforeLength);
    }

    @Test
    @DisplayName("brauerbc: Test empty trip")
    public void emptyplacestest(){
        Places emptyPlaces = new Places();

        TourRequest tr = new TourRequest(3959.0, 1.0, emptyPlaces);
        tr.buildResponse();

        log.info("Braidon emptyPlaces: {}", emptyPlaces);
        log.info("Braidon tr.buildResponse: {}", tr.getOptimizedTour());

        assertEquals(emptyPlaces, tr.getOptimizedTour());
    }
}
