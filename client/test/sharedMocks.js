import { Place } from "../src/models/place.model";
export const VALID_CONFIG_RESPONSE = JSON.stringify({
    requestType: 'config',
    serverName: 't99',
    features: ['config']
});

export const VALID_DISTANCES_RESPONSE = JSON.stringify(
    {
        "earthRadius": 3959.0,
        "places": [
            {
                "latitude": "40.576",
                "name": "Cigarette King Ft Collins",
                "longitude": "-105.058"
            },
            {
                "latitude": "40.437",
                "name": "Hooters Loveland",
                "longitude": "-104.992"
            },
            {
                "latitude": "41.816",
                "name": "Smokes r us",
                "longitude": "-88.277"
            },
            {
                "latitude": "47.913",
                "name": "Snohomish Skatepark",
                "longitude": "-122.085"
            },
            {
                "latitude": "34.021",
                "name": "The Berrics",
                "longitude": "-118.226"
            }
        ],
        "distances": [
            10,
            874,
            1694,
            980,
            852
        ],
        "requestType": "distances"
    }
);

export const VALID_FIND_RESPONSE = JSON.stringify(
    {
        "match": "dave",
        "limit": 10,
        "found": 16,
        "places": [
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
        ],
        "requestType": "find"
    }
);

export const INVALID_REQUEST = JSON.stringify({
    invalid: 'this is an invalid response to fail the schema'
});

export const MOCK_PLACES = [
    new Place({ name: 'Place A', latitude: "40.0", longitude: "-20.0" }),
    new Place({ name: 'Place B', latitude: "-20.0", longitude: "50.0" }),
    new Place({ name: '123 Test', city: 'expanded test', latitude: "50.0", longitude: "60.0"}),
    new Place({lat: '27.0', lng: '100.0', road: 'Main St'}),
    new Place({lat: '80', lng: '-80', suburb: 'Test Suburb', name: 'Test Place'}),
    new Place({house_number: '123', road: 'Main St', suburb: 'Test Suburb', lat: '5.0', lng: '-40.0'}),
    new Place({latitude: "0.0", longitude: "0.0", postcode: '12345'}),
    new Place({lat: '50', lng: '50', road: 'Main St', country: 'Test Country'})
];

export const REVERSE_GEOCODE_RESPONSE = JSON.stringify({
    "place_id": 259127396,
    "licence": "Data © OpenStreetMap contributors, ODbL 1.0. https://osm.org/copyright",
    "osm_type": "relation",
    "osm_id": 8539568,
    "lat": "40.57066025",
    "lng": "-105.08539645568865",
    "place_rank": 30,
    "category": "amenity",
    "type": "university",
    "importance": 0.4948531325947546,
    "addresstype": "amenity",
    "name": "Colorado State University",
    "display_name": "Colorado State University, South College Avenue, Fort Collins, Larimer County, Colorado, 80525-1725, United States",
    "address": {
        "amenity": "Colorado State University",
        "road": "South College Avenue",
        "city": "Fort Collins",
        "county": "Larimer County",
        "state": "Colorado",
        "postcode": "80525-1725",
        "country": "United States",
        "country_code": "us"
    },
    "boundingbox": [
        "40.5527786",
        "40.5789122",
        "-105.0972937",
        "-105.0721817"
    ]
});

export const MOCK_PLACE_RESPONSE = {
    country: "United States",
    defaultDisplayName: "Colorado State University",
    latitude: '40.57',
    longitude: '-105.085',
    //longitude: '-105.08', //Samuel was here, had a disagreement from usePlaces.test.js
    name: 'Colorado State University',
    municipality: 'Fort Collins',
    postcode: "80525-1725",
    region: "Colorado",
    streetAddress: "South College Avenue",
};
