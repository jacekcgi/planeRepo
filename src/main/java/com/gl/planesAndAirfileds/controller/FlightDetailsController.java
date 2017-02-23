package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.service.FlightDetailsDAOService;
import com.gl.planesAndAirfileds.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FlightDetailsController {

    private FlightDetailsDAOService flightDetailsRepository;

    @Autowired
    public FlightDetailsController(FlightDetailsDAOService flightDetailsRepository) {
        this.flightDetailsRepository = flightDetailsRepository;
    }

    @RequestMapping(value = "/getCurrentTime", method = RequestMethod.GET)
    public Long getCurrentTime() {
        return TimeUtil.getCurrentTimeInMillisecondsUTC();
    }

    /**
     * Get from database position of all planes
     *
     * @return
     */
    @RequestMapping(value = "/planeLocation", method = RequestMethod.GET)
    public ResponseEntity<Map<Long, List<FlightDetails>>> getCurrentPositionOfAllPlanes() {
        List<FlightDetails> currentPositionOfAllPlanes = flightDetailsRepository.getLatestFlightDetailsForPlanes(null);
        Map<Long, List<FlightDetails>> planePositions = new HashMap<>();
        planePositions.put(TimeUtil.getCurrentTimeInMillisecondsUTC(), currentPositionOfAllPlanes);
        if (currentPositionOfAllPlanes == null) {
            return new ResponseEntity<Map<Long, List<FlightDetails>>>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<Map<Long, List<FlightDetails>>>(planePositions, HttpStatus.OK);
        }
    }

    /**
     * Get from database position of selected plane
     *
     * @return list of FligtDetails
     */
    @RequestMapping(value = "/planeLocation/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<FlightDetails>> getLatestFlightDetailsForPlane(@PathVariable(value = "id") Long planeId) {
        List<FlightDetails> currentPositionOfAllPlanes = flightDetailsRepository.getLatestFlightDetailsForPlanes(planeId);
        if (currentPositionOfAllPlanes == null) {
            return new ResponseEntity<List<FlightDetails>>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<List<FlightDetails>>(currentPositionOfAllPlanes, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/flightDetails/{plane_id}", method = RequestMethod.GET)
    public FlightDetails latestFightDetailsForPlane(@PathVariable(value = "plane_id") Long planeId) {

        return flightDetailsRepository.getLatestFlightDetailsForPlane(planeId);
    }

}
