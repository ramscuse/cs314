import { useState, useEffect } from 'react';
import { LOG } from '../utils/constants';
import { sendAPIRequest } from '../utils/restfulAPI';

export function useDistances(places, earthRadius, serverURL){
    const [leg, setLeg] = useState([]);
    const [cumulative, setCumulative] = useState([]);
    const [total, setTotal] = useState(0);

    const distances = {
        leg : leg,
        cumulative : cumulative, 
        total : total
    }

    const distanceActions ={
        setLeg : setLeg, 
        setCumulative : setCumulative, 
        setTotal : setTotal
    }

    const context = {leg, cumulative, total};

    useEffect(() => {makeDistancesRequest(places, earthRadius, serverURL, distanceActions, context); 
    }, [places, earthRadius])

    return {distances, distanceActions};
    
}

async function makeDistancesRequest(places, earthRadius, serverURL, distanceActions, context){
    const { setLeg, setCumulative, setTotal } = distanceActions;
    const { leg, cumulative, total} = context;

    const requestBody = {requestType: "distances", places: places, earthRadius: earthRadius };
    const myDistancesResponse = await sendAPIRequest(requestBody, serverURL);

    if(myDistancesResponse){

        const newLeg = [...myDistancesResponse.distances];
        newLeg.unshift(0);
        setLeg(newLeg);

        setTotal(getTotal(myDistancesResponse.distances));

        setCumulative(getCumlative(myDistancesResponse.distances));

    }
    else{
        LOG.error(`Distance request to ${serverURL} failed. Check the log for more details.`, "error");
    }
}

function getTotal(legs) {
    if (typeof(legs) === typeof([])) {
        let newTotal = 0;
        legs.map((leg) => 
            newTotal += parseInt(leg)
        )
        return newTotal;
    } else {
        LOG.error("getTotal failed. Check log for more details");
    }
}

function getCumlative(legs) {
    if (typeof(legs) === typeof([])) {
        let newCumulative = [0];
        legs.map((leg,index) => {
            if (index === 0) {
                newCumulative = [...newCumulative, leg];
            } else {
                newCumulative = [...newCumulative, leg + newCumulative[index]]
            }
        })
        return newCumulative;
    } else {
        LOG.error("getCumlative failed. Check log for more details");
    }
}