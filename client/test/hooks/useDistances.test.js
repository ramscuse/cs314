import React from 'react';
import '@testing-library/jest-dom';
import {screen} from '@testing-library/react';
import { act, renderHook } from '@testing-library/react-hooks';
import { beforeEach, describe, expect, test, toEqual } from '@jest/globals';
import { useDistances } from '../../src/hooks/useDistances';
import { getOriginalServerUrl } from '../../src/utils/restfulAPI';
import makeDistancesRequest from '../../../client/src/hooks/useDistances';
import { VALID_DISTANCES_RESPONSE } from '../sharedMocks';


describe('useDistances', () => {
    const mockPlace1 = {"name": "Cigarette King Ft Collins", "latitude":  "40.576",  "longitude": "-105.058"};
    const mockPlace2 = {"name": "Hooters Loveland", "latitude":  "40.437", "longitude": "-104.992"};
    const mockPlace3 = {"name": "Smokes r us", "latitude":  "41.816", "longitude": "-88.277"};
    const mockPlace4 = {"name": "Snohomish Skatepark", "latitude":  "47.913", "longitude": "-122.085"};
    const mockPlace5 = {"name": "The Berrics", "latitude":  "34.021", "longitude": "-118.226"};

    const mockPlaces = [mockPlace1, mockPlace2, mockPlace3, mockPlace4, mockPlace5];

    const earthRadius = 3959.0;

    const serverUrl = getOriginalServerUrl();

    let hook;

    beforeEach(() => {
        jest.clearAllMocks();
        fetch.resetMocks();
        
    });

    test('stchambe: useless test', async() => {
        expect(5===5);
    });

    
    test('stchambe: basic useDistances test', async () => {
        fetch.mockResponseOnce(VALID_DISTANCES_RESPONSE);
        await act(async () => {
            const { result } = renderHook(() => useDistances(mockPlaces, earthRadius, serverUrl));
            hook = result;
        });
        
        
        expect(hook.current.distances.leg).toEqual([
            0,
            10,
            874,
            1694,
            980,
            852
        ]);
        
    });
    

});


describe('truth is truthy and false is falsy', () => {
    test('true is truthy', () => {
        //screen.debug();
        expect(true).toBe(true);

    });
});


    
