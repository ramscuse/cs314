import React from 'react';
import { beforeEach, describe, expect, jest, test } from '@jest/globals';
import user from '@testing-library/user-event';
import { render, screen, waitFor } from '@testing-library/react';
import AddPlace from '../../../src/components/Header/AddPlace';
import {generateRandom} from '../../../src/components/Header/AddPlace';
import {
	REVERSE_GEOCODE_RESPONSE,
	MOCK_PLACE_RESPONSE,
	VALID_FIND_RESPONSE
} from '../../sharedMocks';

describe('AddPlace', () => {
	const placeObj = {
		latLng: '40.57, -105.085',
		name: 'Colorado State University, South College Avenue, Fort Collins, Larimer County, Colorado, 80525-1725, United States',
	};

	const sampleInputString = 'dave';

	const props = {
		toggleAddPlace: jest.fn(),
		append: jest.fn(),
		isOpen: true,
	};

	beforeEach(() => {
		fetch.resetMocks();
		render(
			<AddPlace
				append={props.append}
				isOpen={props.isOpen}
				toggleAddPlace={props.toggleAddPlace}
			/>
		);
	});

	test('base: validates input', async () => {
		const coordInput = screen.getByTestId('coord-input');
		user.type(coordInput, placeObj.latLng);

		await waitFor(() => {
			expect(coordInput.value).toEqual(placeObj.latLng);
		});
	});

	test('base: handles invalid input', async () => {
		const coordInput = screen.getByTestId('coord-input');
		user.paste(coordInput, '1');

		await waitFor(() => {
			expect(coordInput.value).toEqual('1');
		});

		const addButton = screen.getByTestId('add-place-button');
		expect(addButton.classList.contains('disabled')).toBe(true);
	});

	test('base: Adds place', async () => {
		fetch.mockResponse(REVERSE_GEOCODE_RESPONSE);
		const coordInput = screen.getByTestId('coord-input');

		user.type(coordInput, placeObj.latLng);

		await waitFor(() => {
			expect(coordInput.value).toEqual(placeObj.latLng);
		});

		const addButton = screen.getByTestId('add-latlng-button');
		expect(addButton.classList.contains('disabled')).toBe(false);
		await waitFor(() => {
			user.click(addButton);
		});
		expect(props.append).toHaveBeenCalledWith(MOCK_PLACE_RESPONSE);
	});

	test('stchambe: Adds place from dave', async () =>{
		fetch.mockResponseOnce( VALID_FIND_RESPONSE);
		const coordInput = screen.getByTestId('coord-input');
		user.type(coordInput, sampleInputString);
		const addButton = screen.getByTestId('add-place-button');
		expect(addButton.classList.contains('disabled')).toBe(true);
		await waitFor(() => {
			user.click(addButton);
		});
	});

	test('zplamb: Generate Correct Random Number', async () =>{
		const latUpperRange = 90
		const latLowerRange = -90
		const longUpperRange = 180
		const longLowerRange = -180

		const randomString = generateRandom();
		const stringArray = randomString.split(",")
		const testLat = parseFloat(stringArray[0])
		const testLong = parseFloat(stringArray[1])

		expect(testLat).toBeGreaterThanOrEqual(latLowerRange)
		expect(testLat).toBeLessThanOrEqual(latUpperRange)
		expect(testLong).toBeGreaterThanOrEqual(longLowerRange)
		expect(testLong).toBeLessThanOrEqual(longUpperRange)


	});
		
});
