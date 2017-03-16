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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FlightDetailsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private FlightDetailsService flightDetailsService;

    private static final long UPDATE_INTERVAL = 30000;

    @Autowired
    public FlightDetailsController(FlightDetailsService flightDetailsService) {
        this.flightDetailsService = flightDetailsService;
    }

    @RequestMapping(value = Mappings.GET_CURRENT_TIME, method = RequestMethod.GET)
    public Long getCurrentTime() {
        return TimeUtil.getCurrentTimeInMillisecondsUTC();
    }

    /**
     * Get from database position of all planes or selected one
     *
     * @return
     */
    @RequestMapping(value = {Mappings.FIND_CURRENT_POSITIONS_UPDATE, Mappings.GET_CURRENT_POSITION_UPDATE}, method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public Map<Long, List<FlightDetails>> getCurrentPositionOfAllPlanes(
            @PathVariable Map<String, String> pathVariables) {

        String planeSid = pathVariables.get("sid");
        String lastUpdate = pathVariables.get("last_update");
        Long lastUpdateTime = null;
        if (lastUpdate != null) {
            try {
                lastUpdateTime = Long.valueOf(lastUpdate);
            }
            catch (NumberFormatException e) {
                logger.error(e.getMessage(), e);
            }
        }

        boolean updatePositions = true;
        if (lastUpdateTime != null && (TimeUtil.getCurrentTimeInMillisecondsUTC() - lastUpdateTime < UPDATE_INTERVAL)) {
            updatePositions = false;
        }

        Map<Long, List<FlightDetails>> planePositions = new HashMap<>();
        List<FlightDetails> currentPositionOfAllPlanes = new ArrayList<>();
        if (updatePositions) {
             currentPositionOfAllPlanes = flightDetailsService
                    .getLatestFlightDetailsForPlanes(planeSid);
        }

        planePositions.put(TimeUtil.getCurrentTimeInMillisecondsUTC(), currentPositionOfAllPlanes);
        return planePositions;
    }

    /**
     * Get from database position of selected plane
     *
     * @return list of FligtDetails
     */
    @RequestMapping(value = {Mappings.FIND_CURRENT_POSITIONS, Mappings.GET_CURRENT_POSITION}, method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public Map<Long, List<FlightDetails>> getCurrentPositionOfPlane(@PathVariable Map<String, String> pathVariables) {

        String planeSid = pathVariables.get("sid");
        List<FlightDetails> currentPositionOfOnePlane = flightDetailsService.getLatestFlightDetailsForPlanes(planeSid);
        Map<Long, List<FlightDetails>> planePositions = new HashMap<>();
        planePositions.put(TimeUtil.getCurrentTimeInMillisecondsUTC(), currentPositionOfOnePlane);
        return planePositions;
    }
    @RequestMapping(value = Mappings.GET_FLIGHT_DETAILS, method = RequestMethod.GET)
    public FlightDetails latestFightDetailsForPlane(@PathVariable(value = "plane_sid") String planeSid) {
        return flightDetailsService.getLatestFlightDetailsForPlane(planeSid);
    }

    @RequestMapping(value = Mappings.POST_FLIGHT_DETAILS, method = RequestMethod.POST)
    public ResponseEntity<FlightDetails> gatherFlightDetails(@RequestBody FlightDetails flightDetails) {
        flightDetailsService.insertNewFlightDetails(flightDetails);
        return new ResponseEntity<FlightDetails>(HttpStatus.OK);
    }
}
