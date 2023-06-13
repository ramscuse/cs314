package com.tco.requests;
import static com.tco.misc.DistanceCalculator.calculate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TourRequest extends Request {
    private static final transient Logger log = LoggerFactory.getLogger(TourRequest.class);

    private Double earthRadius;
    private Places places;
    private Double response;
    
    public TourRequest(Double myEarthRadius, Double myResponse, Places myPlaces) {
        super();
        this.requestType = "tour";
        this.earthRadius = myEarthRadius;
        this.response = myResponse;
        this.places = myPlaces;  
    }

    @Override
    public void buildResponse(){
        log.trace("tourRequest -> {}", this);
        this.places = buildTourList();
    }

    private Places buildTourList(){
        log.info("SAMUEL TourRequest given places: {} ", this.places);
        if(this.response<=0.0001 || this.places.size()<=2){
            return this.places; //trivial return same tour as original- SAMUEL
        }
        else{
            return nearestNeighborOptimization(this.places, this.response);
        }
        
    }

    public Places nearestNeighborOptimization(Places originalPlaces, Double timeLimit){
        timeLimit = timeLimit * Math.pow(10,9);
        long startTime = System.nanoTime();
        
        Places shorterTrip = new Places();
        shorterTrip.add(originalPlaces.get(0));
        Places placesToVisit = new Places();
        for(int i = 1; i<originalPlaces.size(); i++){ //make a copy 
            placesToVisit.add(originalPlaces.get(i));
        }

        long timePassed = System.nanoTime() - startTime;
        
        while(placesToVisit.size() >0 && (timePassed < timeLimit)){
            Long min = calculate(shorterTrip.get(shorterTrip.size()-1),placesToVisit.get(0), this.earthRadius);
            Place nearestNeighbor = new Place();
            
            for(Place pl : placesToVisit){
                Long dist = calculate(shorterTrip.get(shorterTrip.size()-1), pl, this.earthRadius);
                if(dist <= min){
                    min = dist;
                    nearestNeighbor = pl;
                }
            }
            shorterTrip.add(nearestNeighbor);
            placesToVisit.remove(nearestNeighbor);
            timePassed = System.nanoTime() - startTime;
        }
        return shorterTrip;
    }

    public Places getOptimizedTour(){
        return this.places;
    }
    public Place getPlace(int tourIndex){
        return places.get(tourIndex);
    }

    public double getRadius(){
        return this.earthRadius;
    }

}
