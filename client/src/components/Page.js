import React, { useEffect, useState } from 'react';
import { Collapse, Button } from 'reactstrap';
import Header from './Header/Header';
import About from './About/About';
import Planner from './Trip/Planner';
import { useToggle } from '../hooks/useToggle';
import { Place } from "../models/place.model";
import { LOG } from '../utils/constants';
import { getOriginalServerUrl, sendAPIRequest } from '../utils/restfulAPI';
import { usePlaces } from '../hooks/usePlaces';

export default function Page(props) {
	const [showAbout, toggleAbout] = useToggle(false);					const [serverSettings, processServerConfigSuccess] = useServerSettings(props.showMessage);		const { places, selectedIndex, placeActions } = usePlaces();		const [tripName, setTripName] = useState('My Trip');
	const [tripTotalDistance, setTotalDistance] = useState(0);			const [earthRadii, setEarthRadii] = useState([["Miles", 3958.8],["Kilometers", 6378.0],["Feet", 20925721.8],["Meters", 6371000],["NauticalMiles", 3443.9],]);
	const [displayNewTrip, toggleDisplayNewTrip] = useToggle(false);	const [backupPlaces, setBackupPlaces] = useState([]);
	async function handleOptimize() {
		const requestBody = {requestType: "tour", earthRadius: 3959, response: 1, places: places}; const tourResponse = await sendAPIRequest(requestBody, serverSettings.serverUrl);
		if(tourResponse){ const optimize = tourResponse.places.map(function (place) { return new Place({...place})}); setBackupPlaces(places); placeActions.setPlaces(optimize); }
		else{ LOG.error(`Tour request to ${serverURL} failed. Check the log for more details.`, "error"); }
	}
	async function handleGoBack() { placeActions.setPlaces(backupPlaces); }
	return (
		<>
			<Header
				toggleAbout={toggleAbout} 		 		showAbout={showAbout} 						placeActions={placeActions}      		disableRemoveAll={!places?.length}
				setTripName={setTripName} 		 		setTotalDistance={setTotalDistance}			serverSettings={serverSettings}  		processServerConfigSuccess={processServerConfigSuccess}
				earthRadii={earthRadii} 		 		setEarthRadii={setEarthRadii}
			/>
			<MainContentArea
				showAbout={showAbout}			 		toggleAbout={toggleAbout}					places={places}					 		selectedIndex={selectedIndex}
				placeActions={placeActions}		 		tripName={tripName}							tripTotalDistance={tripTotalDistance}	showMessage={props.showMessage}
				earthRadii={earthRadii}					handleOptimize={handleOptimize}				displayNewTrip={displayNewTrip}			toggleDisplayNewTrip={toggleDisplayNewTrip}
				handleGoBack={handleGoBack}
			/>
		</>
	);
}

function MainContentArea(props) {
	return (
		<div className='body'>
			<Collapse isOpen={props.showAbout}>
				<About closePage={props.toggleAbout} />
			</Collapse>
			<Collapse isOpen={!props.showAbout} data-testid='planner-collapse'>
				<Planner
					places={props.places}							selectedIndex={props.selectedIndex}
					placeActions={props.placeActions}				tripName={props.tripName}
					showMessage={props.showMessage}					earthRadii={props.earthRadii}
					displayNewTrip={props.displayNewTrip} 			toggleDisplayNewTrip={props.toggleDisplayNewTrip}
					tripTotalDistance={props.tripTotalDistance}		handleOptimize={props.handleOptimize}
					handleGoBack={props.handleGoBack}
				/>
			</Collapse>
		</div>
	);
}

export function useServerSettings(showMessage) {
	const [serverUrl, setServerUrl] = useState(getOriginalServerUrl());
	const [serverConfig, setServerConfig] = useState(null);

	useEffect(() => {
		sendConfigRequest();
	}, []);

	function processServerConfigSuccess(config, url) {
		LOG.info('Switching to Server:', url);
		setServerConfig(config);
		setServerUrl(url);
	}

	async function sendConfigRequest() {
		const configResponse = await sendAPIRequest({ requestType: 'config' },serverUrl);
		if (configResponse) {
			processServerConfigSuccess(configResponse, serverUrl);
		} else {
			setServerConfig(null);
			showMessage(`Config request to ${serverUrl} failed. Check the log for more details.`, 'error');
		}
	}

	return [{ serverUrl: serverUrl, serverConfig: serverConfig }, processServerConfigSuccess,];
}
