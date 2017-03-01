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
            return new ResponseEntity<Map<Long, List<FlightDetails>>>(HttpStatus.SERVICE_UNAVAILABLE);
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
            return new ResponseEntity<Map<Long, List<FlightDetails>>>(HttpStatus.SERVICE_UNAVAILABLE);
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

}
