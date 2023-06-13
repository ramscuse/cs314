package com.tco.requests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestFindRequest {
    FindRequest testerFindReq;

    @Test
    @DisplayName("stchambe: bad url test")
    public void testBadUrl(){
        testerFindReq = new FindRequest("Hillsborough", 13);
        assertEquals(null, testerFindReq.getNumFound());
        assertEquals(13, testerFindReq.getLimit());
        assertEquals(null, testerFindReq.getPlaces());
    }

    

    @Test
    @DisplayName("stchambe: dave find")
    public void testDaveFind(){
        testerFindReq = new FindRequest("dave", 10);
        testerFindReq.buildResponse();
        assertEquals("dave", testerFindReq.getMatch());
        assertEquals(28, testerFindReq.getNumFound());
        assertEquals(10, testerFindReq.getLimit());
        //assertEquals(testerFindReq.getPlaces(), null);
         
        assertEquals("40.0332984924", testerFindReq.getPlaces().get(0).get("latitude"));
        assertEquals("North America", testerFindReq.getPlaces().get(0).get("continent.name"));
        assertEquals("-105.124000549",testerFindReq.getPlaces().get(0).get("longitude"));
        
        // assertEquals(testerFindReq.getPlaces().get(0),
        // {continent=NA, altitude=5170, iso_country=US, latitude=40.0332984924, name=Dave's Airport, municipality=Louisville, iso_region=US-CO, index=1, id=0CO1, longitude=-105.124000549}
        // ); 
        //Samuel - do piecemeal
        
        /*
        assertEquals(tester.getPlaces(), 
            [
        {
            "continent": "NA",
            "altitude": "5170",
            "iso_country": "US",
            "latitude": "40.0332984924",
            "name": "Dave's Airport",
            "municipality": "Louisville",
            "iso_region": "US-CO",
            "index": "1",
            "id": "0CO1",
            "longitude": "-105.124000549"
        },
        {
            "continent": "NA",
            "altitude": "1160",
            "iso_country": "US",
            "latitude": "35.82500076293945",
            "name": "Dave's Place Airport",
            "municipality": "Kingfisher",
            "iso_region": "US-OK",
            "index": "2",
            "id": "1OK1",
            "longitude": "-97.80590057373047"
        },
        {
            "continent": "NA",
            "altitude": "305",
            "iso_country": "US",
            "latitude": "43.739200592041016",
            "name": "Dave Libby Heliport",
            "municipality": "Falmouth",
            "iso_region": "US-ME",
            "index": "3",
            "id": "64ME",
            "longitude": "-70.31289672851562"
        },
        {
            "continent": "NA",
            "altitude": "8400",
            "iso_country": "US",
            "latitude": "38.759700775146484",
            "name": "Dave Nash Ranch Airport",
            "municipality": "Guffey",
            "iso_region": "US-CO",
            "index": "4",
            "id": "7CO1",
            "longitude": "-105.38400268554688"
        },
        {
            "continent": "NA",
            "altitude": "8",
            "iso_country": "US",
            "latitude": "39.318199157714844",
            "name": "Dave's Aerodrome",
            "municipality": "Cedarville",
            "iso_region": "US-NJ",
            "index": "5",
            "id": "7NJ9",
            "longitude": "-75.206298828125"
        },
        {
            "continent": "NA",
            "altitude": "1070",
            "iso_country": "US",
            "latitude": "64.132858",
            "name": "Delta Daves Airport",
            "municipality": "Delta Junction",
            "iso_region": "US-AK",
            "index": "6",
            "id": "AA22",
            "longitude": "-145.804494"
        },
        {
            "continent": "OC",
            "altitude": "3300",
            "iso_country": "PG",
            "latitude": "-8.7819",
            "name": "Kais-Udave Helicopter landing site",
            "iso_region": "PG-CPM",
            "index": "7",
            "id": "AYUD",
            "longitude": "147.2785"
        },
        {
            "continent": "NA",
            "altitude": "700",
            "iso_country": "CA",
            "latitude": "47.809722223",
            "name": "Englehart (Dave's Field)",
            "municipality": "Englehart",
            "iso_region": "CA-ON",
            "index": "8",
            "id": "CDF3",
            "longitude": "-79.8111111111"
        },
        {
            "continent": "AS",
            "iso_country": "IN",
            "latitude": "23.641300520900003",
            "name": "Pandaveswar Airfield",
            "iso_region": "IN-WB",
            "index": "9",
            "id": "IN-0080",
            "longitude": "87.348690033"
        },
        {
            "continent": "NA",
            "altitude": "2421",
            "iso_country": "US",
            "latitude": "47.65359878540039",
            "name": "Davenport Airport",
            "municipality": "Davenport",
            "iso_region": "US-WA",
            "index": "10",
            "id": "K68S",
            "longitude": "-118.16799926757812"
        }
    ]);
    */
    
    }
}
