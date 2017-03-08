package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.domain.api.Mappings;
import com.gl.planesAndAirfileds.service.FlightDetailsService;
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
    private FlightDetailsService flightDetailsService;

    @Autowired
    public FlightDetailsController(FlightDetailsService flightDetailsService) {
        this.flightDetailsService = flightDetailsService;
    }

    @RequestMapping(value = Mappings.GET_CURRENT_TIME, method = RequestMethod.GET)
    public Long getCurrentTime() {
        return TimeUtil.getCurrentTimeInMillisecondsUTC();
    }

    /**
     * Get from database position of all planes
     *
     * @return
     */
    @RequestMapping(value = Mappings.FIND_CURRENT_POSITIONS, method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public Map<Long, List<FlightDetails>> getCurrentPositionOfAllPlanes() {
        List<FlightDetails> currentPositionOfAllPlanes = flightDetailsService.getLatestFlightDetailsForPlanes(null);
        Map<Long, List<FlightDetails>> planePositions = new HashMap<>();
        planePositions.put(TimeUtil.getCurrentTimeInMillisecondsUTC(), currentPositionOfAllPlanes);
        return planePositions;
    }

    /**
     * Get from database position of selected plane
     *
     * @return list of FligtDetails
     */
    @RequestMapping(value = Mappings.GET_CURRENT_POSITION, method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public Map<Long, List<FlightDetails>> getCurrentPositionOfPlane(@PathVariable(value = "id") Long planeId) {
        List<FlightDetails> currentPositionOfOnePlane = flightDetailsService.getLatestFlightDetailsForPlanes(planeId);
        Map<Long, List<FlightDetails>> planePositions = new HashMap<>();
        planePositions.put(TimeUtil.getCurrentTimeInMillisecondsUTC(), currentPositionOfOnePlane);
        return planePositions;
    }

    @RequestMapping(value = Mappings.GET_FLIGHT_DETAILS, method = RequestMethod.GET)
    public FlightDetails latestFightDetailsForPlane(@PathVariable(value = "plane_id") Long planeId) {
        return flightDetailsService.getLatestFlightDetailsForPlane(planeId);
    }

    @RequestMapping( value = Mappings.POST_FLIGHT_DETAILS, method = RequestMethod.POST )
    public ResponseEntity<FlightDetails> gatherFlightDetails(@RequestBody FlightDetails flightDetails) {
        flightDetailsService.insertNewFlightDetails(flightDetails);
        return new ResponseEntity<FlightDetails>(HttpStatus.OK);
    }
}
