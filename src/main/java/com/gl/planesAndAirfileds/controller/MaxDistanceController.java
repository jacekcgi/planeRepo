package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.service.FlightDetailsService;
import com.gl.planesAndAirfileds.service.MaxDistanceCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MaxDistanceController {

    private MaxDistanceCalculatorService maxDistanceCalculatorService;

    private FlightDetailsService flightDetailsService;

    @Autowired
    public MaxDistanceController(MaxDistanceCalculatorService maxDistanceCalculatorService,
                                 FlightDetailsService flightDetailsService) {
        this.maxDistanceCalculatorService = maxDistanceCalculatorService;
        this.flightDetailsService = flightDetailsService;
    }

    @RequestMapping(value = "/planeMaxDistance/{plane_sid}", method = RequestMethod.GET)
    public double getPlaneMaxDistance(@PathVariable(value = "plane_sid") String planeSid) {

        FlightDetails flightDetails = flightDetailsService.getLatestFlightDetailsForPlane(planeSid,true);
        Double reamingFuel = flightDetails.getRemainingFuel();
        Double averageFuelConsumption = flightDetails.getAverageFuelConsumption();

        return maxDistanceCalculatorService.calculateMaxDistance(reamingFuel, averageFuelConsumption);
    }
}
