import React, { useState } from "react";
import { Col, Button, Form, FormGroup, Label, Input, FormText, Modal, ModalHeader, ModalFooter, ModalBody, Collapse } from "reactstrap";

export default function AddUnit(props) {
    
    return (
        <Modal isOpen={props.isOpen} toggle={props.toggleAddUnit}>
            <AddUnitHeader
                toggleAddUnit={props.toggleAddUnit}
            />
            <AddUnitBody
                earthRadii={props.earthRadii}
                setEarthRadii={props.setEarthRadii}
            />
        </Modal>
    );
}

function AddUnitHeader(props) { 
    return (
        <ModalHeader className="m1-2" toggle={props.toggleAddUnit}>
            Add Custom Units
        </ModalHeader>
    );
}

function AddUnitBody(props) {
    const [unitsName,changeUnitsName] = useState("")
    const [newEarthRadius, changeNewEarthRadius] = useState("")
    return (
        <div>
            <UnitForm changeUnitsName = {changeUnitsName} changeNewEarthRadius = {changeNewEarthRadius} />
            <Button className="AddUnit-Button" data-testid="addunit-button" 
                onClick={() => { if (validUnits(unitsName, parseFloat(newEarthRadius))) { const rad = parseFloat(newEarthRadius); props.setEarthRadii(prevEarthRadii => [...prevEarthRadii, [unitsName,rad]]);}}} 
                color='primary'>Add Unit</Button>
            <Collapse isOpen={Array.isArray(props.earthRadii) && props.earthRadii.length > 0}>
                {Array.isArray(props.earthRadii) && props.earthRadii.map((radius, index) => {
                    return (
                        <div key={index} style={{display : "flex", marginBottom : "1em", marginLeft: "5%"}}>
                            {radius[0]} - {radius[1]}
                            <Button size='sm' style={{marginLeft : "auto", marginRight: "5%"}}
                                onClick={() => { props.setEarthRadii(prevEarthRadii => prevEarthRadii.filter(element => { return element[0] !== radius[0] }))}}
                                data-testid='removeunit-button'>Remove</Button>
                        </div>
                    )
                })}
            </Collapse>
        </div>
    )
}

function UnitForm(props) {
    return (
        <ModalBody>
            <FormGroup row>
                <Label for="Units Name" style={{marginBottom: 5}} sm={3}>Units Name</Label>
                <Col sm={8}>
                    <Input type="name" id="addEarthRadiusName" onChange={ev => props.changeUnitsName(ev.target.value)} placeholder="type here" data-testid="name-input"/>
                </Col>
                <Label for="Earth Radius" sm={3}>Earth Radius</Label>
                <Col sm={8}>
                    <Input type="name" id="addEarthRadius" onChange={ev => props.changeNewEarthRadius(ev.target.value)} placeholder="type here" data-testid="radius-input" />
                </Col>
            </FormGroup>
        </ModalBody>
    )
}

export function validUnits(unitsName, unitsRadius) {
    if (typeof unitsName == "string" && typeof unitsRadius == "number") {
        if (unitsRadius > 0) {
            return true
        }
        return false
    }
    return false
}