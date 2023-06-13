import React, { useState } from 'react';
import { useToggle } from '../../../hooks/useToggle';
import { Table, Collapse, Button } from 'reactstrap';
import { latLngToText, placeToLatLng } from '../../../utils/transformers';
import { BsChevronDown } from 'react-icons/bs';
import PlaceActions from './PlaceActions';
import {Dropdown, DropdownToggle, DropdownMenu, DropdownItem} from 'reactstrap';
import { useDistances }  from '../../../hooks/useDistances';
import { getOriginalServerUrl, sendAPIRequest } from '../../../utils/restfulAPI';
import { handleOptimize, useServerSettings } from "../../Page"

export default function Itinerary(props) {
	const [earthRadius, setEarthRadius] = useState(3958.8);
	const [serverSettings, processServerConfigSuccess] = useServerSettings( props.showMessage) ;
	const {distances, distanceActions} = useDistances(props.places, earthRadius, serverSettings.serverUrl);
	return (
		<div>
			<Table responsive>
				<TripHeader
					tripName={props.tripName}
					setEarthRadius={setEarthRadius}
					earthRadii={props.earthRadii}
				/>
				<PlaceList
					places={props.places}					placeActions={props.placeActions}
					selectedIndex={props.selectedIndex}		distances={distances}
					distanceActions={distanceActions}		leg = {distances.leg}
					cumulative = {distances.cumulative}		total={distances.total}
				/>
			</Table>
			{!props.displayNewTrip && <Button className='display-new-trip-button' data-testid='display-new-trip-button' color='primary' onClick={() => {props.toggleDisplayNewTrip(); props.handleOptimize()}}>Optimize Trip</Button>}
			{props.displayNewTrip && <Button className='keep-trip-button' data-testid='keep-trip-button' style={{marginRight: "5px"}} color='primary' onClick={() => props.toggleDisplayNewTrip()}>Keep Trip</Button>}
			{props.displayNewTrip && <Button className='go-back-button' data-testid='go-back-button' color='secondary' onClick={() => {props.toggleDisplayNewTrip(); props.handleGoBack()}}>Go Back</Button>}
		</div>
	);
}

function TripHeader(props) {
	const [unitDropDown, setUnitDropDown] = useState(false); const toggle = () => setUnitDropDown((prevState) => !prevState);
	return (
		<thead>
			<tr className="header-distances">
				<th>
					<Dropdown className="unit-dropdown-menu" isOpen={unitDropDown} toggle={toggle} direction="down">
						<DropdownToggle caret>Units</DropdownToggle>
						<DropdownMenu>
							{Array.isArray(props.earthRadii) && props.earthRadii.map((unit, index) => { return ( <DropdownItem key={index} onClick={() => props.setEarthRadius(unit[1])}>{unit[0]}</DropdownItem> )})}
						</DropdownMenu>
					</Dropdown>
				</th>
			</tr>
			<tr className="spacer">
				<th className='trip-header-title' data-testid='trip-header-title'>{props.tripName}</th>
				<th className="header-legdistance-text">Leg Distance</th>
				<th className="header-cumdistance-text">Cumulative Distance</th>
			</tr>
		</thead>
	);
}

function PlaceList(props) {
	return (
		<tbody>
			{props.places.map((place, index) => (
				<PlaceRow
					key={`table-${JSON.stringify(place)}-${index}`}
					place={place}
					placeActions={props.placeActions}
					selectedIndex={props.selectedIndex}
					index={index}
					distances={props.distances}
					distanceActions={props.distanceActions}
					leg = {props.distances.leg[index]}
					cumulative = {props.distances.cumulative[index]}
				/>
			))}
			<tr>
				<th>Round Trip Distance : </th>
				<th/>
				<th>{props.total}</th>
			</tr>
		</tbody>
	);
}

function PlaceRow(props) {
	const [showFullName, toggleShowFullName] = useToggle(false);		const name = props.place.defaultDisplayName;
	const location = latLngToText(placeToLatLng(props.place));			const legDistance = props.distances.leg;
	const cumulativeDistance = props.distances.cumulative;				const distanceUnits = "miles"; 
	const index = props.index;
	return (
		<tr className={props.selectedIndex === props.index ? 'selected-row' : ''}>
			<td
				data-testid={`place-row-${props.index}`}
				onClick={() => placeRowClicked( toggleShowFullName, props.placeActions.selectIndex, props.index )}
			>
				<p>{name}</p>
				<AdditionalPlaceInfo showFullName={showFullName} location={location} legDistance={legDistance} distanceUnits={distanceUnits} placeActions={props.placeActions} index={props.index} place={props.place}/>
			</td>
			<td> {legDistance[index]}</td>
			<td>{cumulativeDistance[index]}</td> 
			<RowArrow toggleShowFullName={toggleShowFullName} index={props.index}/>
		</tr>
	);
}

function AdditionalPlaceInfo(props) {
	return (
		<Collapse isOpen={props.showFullName}>
			{props.place.formatPlace().replace(`${props.place.defaultDisplayName}, `, '')}
			<br />
			{props.location}
			<br />
			<p>Distance : {props.legDistance[props.index]} {props.distanceUnits}</p>
			<br />
			<PlaceActions placeActions={props.placeActions} index={props.index} />
		</Collapse>
	);
}

function placeRowClicked(toggleShowFullName, selectIndex, placeIndex) {
	toggleShowFullName();
	selectIndex(placeIndex);
}

function RowArrow(props) {
	return (
		<td>
			<BsChevronDown data-testid={`place-row-toggle-${props.index}`} onClick={props.toggleShowFullName}/>
		</td>
	);
}
