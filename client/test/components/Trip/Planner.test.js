import React from 'react';
import Planner from '../../../src/components/Trip/Planner';
import { beforeEach, describe, test, jest, expect } from '@jest/globals';
import { render, screen, waitFor } from '@testing-library/react';

describe('Planner', () => {
	const plannerProps = {
		createSnackBar: jest.fn(),
		places: [],
		selectedIndex: -1,
		placeActions: {
			append: jest.fn(),
		},
	};

	beforeEach(() => {
		render(<Planner {...plannerProps} />);
	});

	test('base: renders a Leaflet map', async () => {
		screen.getByText('Leaflet');
	});

	test('base: renders trip table', async () => {
		screen.getByTestId('trip-header-title');
	});
});
