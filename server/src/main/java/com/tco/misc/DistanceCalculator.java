package com.tco.misc;

import static java.lang.Math.round;
import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.lang.Math.atan2;
import static java.lang.Math.sin;
import static java.lang.Math.cos;

public final class DistanceCalculator{
    final static boolean useRandom = false;
    public DistanceCalculator() {}

    
    public static Long calculate(GeographicCoordinate from, GeographicCoordinate to, double earthRadius){
        double lat1 = from.latRadians();
        double lat2 = to.latRadians();
        double deltaLambda = abs(from.lonRadians() - to.lonRadians());
        // c = sqrt(a^2 + b^2); a = termOne, b = termTwo, c = numerator
        double termOne = cos(lat2)*sin(deltaLambda);
        double termTwo = cos(lat1)*sin(lat2) - (sin(lat1)*cos(lat2)*cos(deltaLambda));
        double numerator = sqrt(pow(termOne,2) + pow(termTwo,2));
        double denominator = sin(lat1)*sin(lat2) + (cos(lat1)*cos(lat2)*cos(deltaLambda));
        double deltaSigma = atan2(numerator, denominator);
        return round(earthRadius * deltaSigma);
      }
    /*
      public static Long calculate(GeographicCoordinate from, GeographicCoordinate to, double earthRadius){
        return 72L;
      }
     */ 
}