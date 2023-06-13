import React, { useEffect, useState } from 'react';
import {
	Button,
	Col,
	Modal,
	ModalBody,
	ModalHeader,
	Input,
	Collapse,
	ModalFooter,
} from 'reactstrap';
import Coordinates from 'coordinate-parser';
import { reverseGeocode } from '../../utils/reverseGeocode';
import { Place } from "../../models/place.model";
import { getOriginalServerUrl, sendAPIRequest } from '../../utils/restfulAPI';

export default function AddPlace(props) {
	const [foundPlace, setFoundPlace] = useState();
	const [inputString, setinputString] = useState('');
	const [serverUrl, setServerUrl] = useState(getOriginalServerUrl());
	const [listOfPlaces, setListOfPlaces] = useState([]);

	return (
		<Modal isOpen={props.isOpen} toggle={props.toggleAddPlace}>
			<AddPlaceHeader toggleAddPlace={props.toggleAddPlace} />
			<PlaceSearch
				append={props.append}
				foundPlace={foundPlace}
				setFoundPlace={setFoundPlace}
				inputString={inputString}
				setinputString={setinputString}
				serverUrl = {serverUrl}
				listOfPlaces = {listOfPlaces}
				setListOfPlaces = {setListOfPlaces}
			/>
			<AddPlaceFooter
				setinputString={setinputString}
				foundPlace={foundPlace}
				setFoundPlace={setFoundPlace}
				listOfPlaces = {listOfPlaces}
				setListOfPlaces = {setListOfPlaces}
			/>
		</Modal>
	);
}

function AddPlaceHeader(props) {
	return (
		<ModalHeader className='ml-2' toggle={props.toggleAddPlace}>
			Add or Find a Place
		</ModalHeader>
	);
}

function PlaceSearch(props) {
	useEffect(() => {
		verifyInput(props.inputString, props.setFoundPlace, props.serverUrl, props.setListOfPlaces);
	}, [props.inputString]);

	return (
		<ModalBody>
			<Col>
				<Input
					onChange={(input) => props.setinputString(input.target.value)}
					placeholder='Enter Place Coordinates or Name'
					data-testid='coord-input'
					value={props.inputString}
				/>
				<PlaceInfo foundPlace={props.foundPlace} listOfPlaces={props.listOfPlaces} append={props.append}/>
			</Col>
		</ModalBody>
	);
}

function PlaceInfo(props) {
	return (
		
		<Collapse isOpen={!!props.foundPlace || props.listOfPlaces.length > 0}>
			
			<br />
			{props.listOfPlaces.map((place,index) => { 
				console.log(place);
				return (
				<div key={index} style={{display : "flex", marginBottom : "1em" }}>
					{place?.name} {','} {place?.municipality} {','} {place.region} {','} {place.country}
					<Button
						className = "addPlace-Button"
						color='primary'
						size='sm'
						style={{marginLeft : "auto"}}
						onClick={() => {
							props.append(place);
						}}
						data-testid='add-place-button'
					>
						Add Place
					</Button>
				</div>
			)})}

			{!!props.foundPlace && <div style={{display : "flex"}}>
				{props.foundPlace?.formatPlace()}
				<Button
					className = "addPlace-Button"
					color='primary'
					size='sm'
					style={{marginLeft : "auto"}}
					onClick={() => {
						props.append(props.foundPlace);
					}}
					disabled = {!props.foundPlace}
					data-testid='add-latlng-button'
				>
					Add Place
				</Button>
			</div>}
		
		</Collapse>
	);
}

function AddPlaceFooter(props) {
	return (
		<ModalFooter>
			<Button
				color='primary'
				onClick={() => {
					props.setinputString('');
					props.setFoundPlace('');
					props.setListOfPlaces([])
				}}
				data-testid='add-place-button'
				disabled = {!props.foundPlace && props.listOfPlaces.length < 1}
			>
				Clear Search
			</Button>

			<Button
				color='primary'
				onClick={() => {
					let randomPlace = generateRandom()
					props.setinputString(randomPlace);
					<PlaceSearch value={props.inputString}/>
				}}
			>
				Random Place
			</Button>
		</ModalFooter>
	);
}

async function verifyInput(inputString, setFoundPlace, serverUrl, setListOfPlaces) {
	try {
		if (isStringValid(inputString)) {
			const requestBody = {   
				requestType: "find",
				"match" : inputString, 
				"limit" : 5
			}
			const myFindResponse = await sendAPIRequest(requestBody, serverUrl);
    		if(myFindResponse){
				const newPlaces = myFindResponse.places.map(function (place) {
					return new Place({...place})
				})
				setListOfPlaces(newPlaces);
    		}
    		else{
        		LOG.error(`Find request to ${serverURL} failed. Check the log for more details.`, "error");
    		}
		} else {
			const latLngPlace = new Coordinates(inputString);
			const lat = latLngPlace.getLatitude();
			const lng = latLngPlace.getLongitude();
			if (isLatLngValid(lat,lng)) {
				const fullPlace = await reverseGeocode({ lat, lng });
				setFoundPlace(fullPlace);
			} else {
				setFoundPlace(undefined);
			}
		}
	} catch (error) {
		setFoundPlace(undefined);
	}
}

function isLatLngValid(lat,lng) {
	let re = /-?\d*.?\d+/;
	return (re.test(lat) && re.test(lng))
}

function isStringValid(input) {
	let re = /\d+/;
	return (input.length > 2 && !(re.test(input)));
}


export function generateRandom() {
	let lat1 = Math.random() * (80 + 1)
	lat1 *= Math.round(Math.random()) ? 1 : -1
	let long1 = Math.random() * (170 + 1)
	long1 *= Math.round(Math.random()) ? 1 : -1 

	let coordinate_string = lat1.toString()

	coordinate_string += ","
	coordinate_string += long1.toString()

	return coordinate_string;
}