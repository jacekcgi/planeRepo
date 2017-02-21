package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.service.FlightDetailsDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlightDetailsController {

    private FlightDetailsDAOService flightDetailsRepository;

    @Autowired
    public FlightDetailsController(FlightDetailsDAOService flightDetailsRepository) {
        this.flightDetailsRepository = flightDetailsRepository;
    }

    /**
     * Get from database position of all planes
     * @return
     */
    @RequestMapping( value = "/planeLocation", method = RequestMethod.GET )
    public ResponseEntity<List<FlightDetails>> getCurrentPositionOfAllPlanes() {
        List<FlightDetails> currentPositionOfAllPlanes = flightDetailsRepository.getLatestFlightDetailsForPlanes(null);
        if(currentPositionOfAllPlanes == null) {
            return  new ResponseEntity<List<FlightDetails>>(HttpStatus.BAD_REQUEST);
        } else {
            return  new ResponseEntity<List<FlightDetails>>(currentPositionOfAllPlanes,HttpStatus.OK);
        }
    }

    /**
     * Get from database position of selected plane
     * @return list of FligtDetails
     */
    @RequestMapping( value = "/planeLocation/{id}", method = RequestMethod.GET )
    public ResponseEntity<List<FlightDetails>> getCurrentPositionOfPlane(@PathVariable(value="id") Long planeId) {
        List<FlightDetails> currentPositionOfAllPlanes = flightDetailsRepository.getLatestFlightDetailsForPlanes(planeId);
        if(currentPositionOfAllPlanes == null) {
            return  new ResponseEntity<List<FlightDetails>>(HttpStatus.BAD_REQUEST);
        } else {
            return  new ResponseEntity<List<FlightDetails>>(currentPositionOfAllPlanes,HttpStatus.OK);
        }
    }

}
