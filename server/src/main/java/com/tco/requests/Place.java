package com.tco.requests;

import static java.lang.Double.parseDouble;
import static java.lang.Math.toRadians;

import com.tco.misc.GeographicCoordinate;
import java.util.HashMap;

public class Place extends HashMap<String,String> implements GeographicCoordinate {
    // For testing purposes
    String latStr;
    String lonStr;

    Place(){}

    public Place(String lat, String lon) {
        this.put("latitude", lat);
        this.put("longitude", lon);
        this.latStr = "myLat" + lat;
        this.lonStr = "myLon" + lon;
    }

    public Double latRadians() {
        return toRadians(parseDouble(this.get("latitude")));
    }

    public Double lonRadians() {
        return toRadians(parseDouble(this.get("longitude")));
    }
 
    //what is id() for??
    public String id(){
        return this.get("id");
    }

    public String getLatStr() {return latStr; }
    public String getLonStr() {return lonStr; }
}
