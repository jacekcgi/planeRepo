package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.domain.api.Mappings;
import com.gl.planesAndAirfileds.dto.FlightDetailsDto;
import com.gl.planesAndAirfileds.service.FlightDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

     /**
     * Get from database position of selected plane
     *
     * @return list of FligtDetails
     */
    @RequestMapping(value = {Mappings.FIND_CURRENT_POSITIONS, Mappings.GET_CURRENT_POSITION}, method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public FlightDetailsDto getCurrentPositionOfPlane(@PathVariable Map<String, String> pathVariables) {

        List<FlightDetails> flightDetailsDtos = flightDetailsService.findLatestFlightDetails();

//        List<FlightDetails> currentPositionOfOnePlane = flightDetailsService.getLatestFlightDetailsForPlanes(planeSid,false);
//        return new FlightDetailsDto(TimeUtil.getCurrentTimeInMillisecondsUTC(), currentPositionOfOnePlane);
        return null;
    }

    @RequestMapping(value = Mappings.GET_FLIGHT_DETAILS, method = RequestMethod.GET)
    public FlightDetails latestFightDetailsForPlane(@PathVariable(value = "plane_sid") String planeSid) {
        return flightDetailsService.getLatestFlightDetailsForPlane(planeSid,true);
    }

//    @RequestMapping(value = Mappings.POST_FLIGHT_DETAILS, method = RequestMethod.POST)
//    public ResponseEntity<FlightDetails> gatherFlightDetails(@RequestBody FlightDetails flightDetails) {
//        flightDetailsService.insertNewFlightDetails(flightDetails);
//        return new ResponseEntity<FlightDetails>(HttpStatus.OK);
//    }
}
