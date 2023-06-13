import React, { useState } from "react";
import { beforeEach, describe, expect, jest, test } from '@jest/globals';
import user from '@testing-library/user-event';
import { render, screen, waitFor } from '@testing-library/react';
import AddUnit from '../../../src/components/Header/AddUnit';
import {handleSubmit,validUnits}from '../../../src/components/Header/AddUnit';

describe('AddUnit', () => {
	const unitObject = {
		name: 'test',
		earthRadius: '3000',
	};

	const badUnitObject = {
		name: '3000',
		earthRadius: 'test',
	};

	const props = {
		toggleAddUnit: jest.fn(),
		earthRadii: [["Miles", 3958.8]],
		setEarthRadii: jest.fn(),
		isOpen: true,
	};

	beforeEach(() => {
		render(
			<AddUnit
				earthRadii = {props.earthRadii}
				setEarthRadii={props.setEarthRadii}
				isOpen={props.isOpen}
				toggleAddUnit={props.toggleAddUnit}
			/>
		);
	});

	test('base: validates input name', async () => {
		const nameInput = screen.getByTestId('name-input');
		user.type(nameInput, unitObject.name);

		await waitFor(() => {
			expect(nameInput.value).toEqual(unitObject.name);
		});
	});

    test('base: validates input radius', async () => {
		const radiusInput = screen.getByTestId('radius-input');
		user.type(radiusInput, unitObject.earthRadius);

		await waitFor(() => {
			expect(radiusInput.value).toEqual(unitObject.earthRadius);
		});
	});

    test('base: invalidates input', async () => {
        await waitFor(() => {
            expect(validUnits("hello","test")).toEqual(false);
        })
        await waitFor(() => {
            expect(validUnits("hello",0)).toEqual(false);
        })
        await waitFor(() => {
            expect(validUnits("hello",500)).toEqual(true);
        })
    })

	test('base: addUnit add button', async () => {
		const nameInput = screen.getByTestId('name-input');
		const radiusInput = screen.getByTestId('radius-input');
		user.type(nameInput, unitObject.name);
		user.type(radiusInput, unitObject.earthRadius);

		await waitFor(() => {
			expect(radiusInput.value).toEqual(unitObject.earthRadius);
		});
		await waitFor(() => {
			expect(nameInput.value).toEqual(unitObject.name);
		});

		const addButton = screen.getByTestId('addunit-button')
		await waitFor(() => {
			user.click(addButton);
		});
		expect(props.setEarthRadii).toBeCalled();
	})

	test('base: addUnit remove button', async () => {
		const removeButton = screen.getByTestId('removeunit-button')
		await waitFor(() => {
			user.click(removeButton);
		});
		expect(props.setEarthRadii).toBeCalled();
	})
});
