package com.tco.requests;

import com.tco.requests.Place;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.tco.misc.DistanceCalculator.calculate;

public class DistancesRequest extends Request {

    private static final transient Logger log = LoggerFactory.getLogger(DistancesRequest.class);

    private double earthRadius;
    private Places places;
    private Distances distances;

    @Override
    public void buildResponse() {
        log.info("SAMUEL: buildResponse()");
        distances=buildDistanceList();
        log.trace("distanceResponse -> {}", this);
    }

    private Distances buildDistanceList() {
        log.info("SAMUEL start buildDistanceList()");
        Distances distances = new Distances();

        log.info("SAMUEL places.size() = {}", places.size());
        if(places.size()==0) { 
            return distances;}
        if(places.size()==1){
            distances.add(0L);
            return distances;
        }
        
        for (int i = 0; i < places.size(); i++) {
            Place thisPlace = places.get(i);
            log.info("SAMUEL: place num:{} {} {}", i, places.get(i).getLatStr(), places.get(i).getLonStr());
            Place nextPlace = places.get((i+1) % places.size());
            log.info("SAMUEL: place num:{} {} {}", i, places.get((i+1)%places.size()).getLatStr(), places.get((i+1)%places.size()).getLonStr());
            Long distanceBetween = calculate(thisPlace, nextPlace, earthRadius);
            log.info("distanceBetween {}", distanceBetween);
            distances.add(distanceBetween);

            /*
            log.info("SAMUEL: place num:{} {} {}", i, places.get(i).getLatStr(), places.get(i).getLonStr());

            if(i==places.size()-1){
                Place lastPlace = places.get(i);
                Long homeStretch = calculate(places.get(0), lastPlace, earthRadius);
                distances.add(homeStretch);
                log.info("homeStretch dist: {}", homeStretch);
                return distances;
            }
            else{
                Place currPlace = places.get(i);
                Place nextPlace = places.get(i+1);
                Long distanceBetween = calculate(currPlace, nextPlace, earthRadius);
                distances.add(distanceBetween);
                log.info("distanceBetween: {}", distanceBetween);
            }
            */
        }
        log.info("SAMUEL Display distances: {}", distances);
        log.info("SAMUEL end buildDistanceList()");
        return distances;
    }

  /* The following methods exist only for testing purposes and are not used
  during normal execution, including the constructor. */

    public DistancesRequest(double earthRadius, Places placesArg) {
        super();
        this.requestType = "distances";
        this.earthRadius = earthRadius;
        this.places = placesArg;
        log.info("DistanceRequest ctor");
    }

    public double earthRadius(){
        return this.earthRadius;
    }

    public Places places(){
        return this.places;
    }

    public Distances distances(){
        return this.distances;
    }
}
