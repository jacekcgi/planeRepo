package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.service.FlightDetailsDAOService;
import com.gl.planesAndAirfileds.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FlightDetailsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private FlightDetailsDAOService flightDetailsDAOService;

    @Autowired
    public FlightDetailsController(FlightDetailsDAOService flightDetailsDAOService) {
        this.flightDetailsDAOService = flightDetailsDAOService;
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
        List<FlightDetails> currentPositionOfAllPlanes = flightDetailsDAOService.getLatestFlightDetailsForPlanes(null);
        if (currentPositionOfAllPlanes == null) {
            return new ResponseEntity<Map<Long, List<FlightDetails>>>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            Map<Long, List<FlightDetails>> planePositions = new HashMap<>();
            planePositions.put(TimeUtil.getCurrentTimeInMillisecondsUTC(), currentPositionOfAllPlanes);
            return new ResponseEntity<Map<Long, List<FlightDetails>>>(planePositions, HttpStatus.OK);
        }
    }

    /**
     * Get from database position of selected plane
     *
     * @return list of FligtDetails
     */

    @RequestMapping(value = "/planeLocation/{id}", method = RequestMethod.GET)
    public ResponseEntity<Map<Long, List<FlightDetails>>> getCurrentPositionOfPlane(@PathVariable(value = "id") Long planeId) {
        List<FlightDetails> currentPositionOfOnePlane = flightDetailsDAOService.getLatestFlightDetailsForPlanes(planeId);
        if (currentPositionOfOnePlane == null) {
            return new ResponseEntity<Map<Long, List<FlightDetails>>>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            Map<Long, List<FlightDetails>> planePositions = new HashMap<>();
            planePositions.put(TimeUtil.getCurrentTimeInMillisecondsUTC(), currentPositionOfOnePlane);
            return new ResponseEntity<Map<Long, List<FlightDetails>>>(planePositions, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/flightDetails/{plane_id}", method = RequestMethod.GET)
    public FlightDetails latestFightDetailsForPlane(@PathVariable(value = "plane_id") Long planeId) {
         return flightDetailsDAOService.getLatestFlightDetailsForPlane(planeId);
    }

    @RequestMapping( value = "/flightDetails", method = RequestMethod.POST )
    public ResponseEntity<FlightDetails> gatherFlightDetails(@RequestBody FlightDetails flightDetails) {
        flightDetailsDAOService.insertNewFlightDetails(flightDetails);
        return new ResponseEntity<FlightDetails>(HttpStatus.OK);
    }
}
