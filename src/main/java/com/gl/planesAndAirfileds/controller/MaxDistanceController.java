package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.service.MaxDistanceCalculatorService;
import com.gl.planesAndAirfileds.service.impl.FlightDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MaxDistanceController {

    private MaxDistanceCalculatorService maxDistanceCalculatorService;
    private FlightDetailsServiceImpl flightDetailsServiceImp;

    public MaxDistanceCalculatorService getMaxDistanceCalculatorService() {
        return maxDistanceCalculatorService;
    }

    @Autowired
    public void setMaxDistanceCalculatorService(MaxDistanceCalculatorService maxDistanceCalculatorService) {
        this.maxDistanceCalculatorService = maxDistanceCalculatorService;
    }

    public FlightDetailsServiceImpl getFlightDetailsDAOService() {
        return flightDetailsServiceImp;
    }

    @Autowired
    public void setFlightDetailsDAOService(FlightDetailsServiceImpl flightDetailsServiceImp) {
        this.flightDetailsServiceImp = flightDetailsServiceImp;
    }

    @RequestMapping(value = "/planeMaxDistance/{plane_id}", method = RequestMethod.GET)
    public double getPlaneMaxDistance(@PathVariable(value = "plane_id") Long planeId) {

        FlightDetails flightDetails = flightDetailsServiceImp.getLatestFlightDetailsForPlane(planeId);
        Double reamingFuel = flightDetails.getRemainingFuel();
        Double averageFuelConsumption = flightDetails.getAverageFuelConsumption();

        return maxDistanceCalculatorService.calculateMaxDistance(reamingFuel,averageFuelConsumption);
    }
}
