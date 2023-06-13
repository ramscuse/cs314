package com.tco.misc;


import static com.tco.misc.DistanceCalculator.calculate;
import static com.tco.misc.DistanceCalculator.useRandom;

import static java.lang.Math.PI;
import static java.lang.Math.round;
import static java.lang.Math.toRadians;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDistanceCalculator {
    final static Logger log = LoggerFactory.getLogger(TestDistanceCalculator.class);
    static class Geo implements GeographicCoordinate {
        Double degreesLatitude;
        Double degreesLongitude;

        public Geo(Double lat, Double lon) {
            this.degreesLatitude = lat;
            this.degreesLongitude = lon;
            log.info("SAMUEL: Geo ctor in TestDistanceCalc");
        }

        public Double latRadians() {
            return toRadians(degreesLatitude);
        }

        public Double lonRadians() {
            return toRadians(degreesLongitude);
        }
    }


    final Geo origin = new Geo(0., 0.);
    

    final static Long small = 1L;
    final static Long piSmall = round(PI*small);
    final static Long piSmallHalf = round(PI/2.0 * small);

    final static Long big = 1000000L;
    final static Long piBig = Math.round(PI*big);
    final static Long piBigHalf = Math.round(PI/2.0 * big);


    @Test
    @DisplayName("stchambe: distance to self should be zero")
    public void testDistanceToSelf(){
        log.info("SAMUEL TestDistCalc distToSelf");
        assertEquals(0, calculate(origin, origin, small));
    }


    @Test
      @DisplayName("stchambe: Test Cigarette King (FoCO) to Smokes R Us (Aurora, Illinois)")
      public void testCigKingDistance(){
        Geo CigaretteKing = new Geo(40.576, -105.058);
        Geo SmokesRUs = new Geo(41.816, -88.277);
        log.info("SAMUEL testDistanceCalc cig king test");
        log.info("CigKing lat: {}", CigaretteKing.latRadians());
        log.info("CigKing long: {}", CigaretteKing.lonRadians());
        log.info("CigKing -> Smokes dist: {}", calculate(CigaretteKing, SmokesRUs, 3959));
        assertEquals(875L, calculate(CigaretteKing, SmokesRUs, 3959));
      }

    @Test
    @DisplayName("arackow: Test DistanceCalculation rounding")
    public void testRoundedDistance(){
        // Arbitrary values selected to give a roundable distance, in this case 872.6646
        // according to https://www.vcalc.com/wiki/Great%20Circle%20Distance
        final Geo pointOne = new Geo(0., 125.);
        final Geo pointTwo = new Geo(0., 100.);
        final Long earthRadius = 2000L;
        final Long targetDistance = 873L;
        assertEquals(targetDistance, calculate(pointOne, pointTwo, earthRadius));
    }
    
    @Test
    @DisplayName("arackow: Test equator equivalence")
    public void testEquatorEquivalence(){
        final Geo pointOne = new Geo(0.,180.);
        final Geo pointTwo = new Geo(0., -180.);
        final Long earthRadius = big;
        final Long targetDistance = 0L;
        assertEquals(targetDistance, calculate(pointOne, pointTwo, earthRadius));
    }

}
