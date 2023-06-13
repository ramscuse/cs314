package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FindRequest extends Request {

    private static final transient Logger log = LoggerFactory.getLogger(FindRequest.class);
    
    private String match;
    private Integer limit;
    private Integer found;
    private Places places;
    
    public FindRequest(String newMatch, Integer newLimit){
        this.requestType = "find";
        this.match = newMatch;
        if(newLimit == 0){
            this.limit = 101;
        }
        else{
            this.limit = newLimit;
        }
    } 

    @Override
    public void buildResponse(){
        log.info("findRequest buildResponse()");
        log.trace("findRequest -> {}", this);
        this.places = buildFindList();
    }


    private Places buildFindList(){
        Credential.whichEnv();
        log.info("SAMUEL buildFindList, dburl {}", Credential.url());
        try{
            if(limit == 0){
                places = Database.places(match, Database.found(match));
            }
            else{
                places = Database.places(match, this.limit);
            }
            
            this.found = Database.found(match); 
            return places;
        }
        catch(Exception e){
            log.info(e.getMessage());
            places = new Places();
            this.limit = -1;
            this.found = -1;
            return places;
        }  
        
    }

    public Integer getLimit(){
        return this.limit;
    }

    public String getMatch(){
        return this.match;
    }

    public Integer getNumFound(){
        return this.found;
    }

    public Places getPlaces(){
        return this.places;
    }
    
}
