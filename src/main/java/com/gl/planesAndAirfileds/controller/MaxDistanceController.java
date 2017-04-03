package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.domain.api.Mappings;
import com.gl.planesAndAirfileds.service.FlightDetailsService;
import com.gl.planesAndAirfileds.service.MaxDistanceCalculatorService;
import com.gl.planesAndAirfileds.service.PrimitiveConverterHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MaxDistanceController extends AbstractController {

    private MaxDistanceCalculatorService maxDistanceCalculatorService;

    private FlightDetailsService flightDetailsService;

    private PrimitiveConverterHelperService primitiveConverterHelperService;

    @Autowired
    public MaxDistanceController(MaxDistanceCalculatorService maxDistanceCalculatorService,
                                 FlightDetailsService flightDetailsService) {
        this.maxDistanceCalculatorService = maxDistanceCalculatorService;
        this.flightDetailsService = flightDetailsService;
    }

    public PrimitiveConverterHelperService getPrimitiveConverterHelperService() {
        return primitiveConverterHelperService;
    }

    @Autowired
    public void setPrimitiveConverterHelperService(
            PrimitiveConverterHelperService primitiveConverterHelperService) {
        this.primitiveConverterHelperService = primitiveConverterHelperService;
    }

    @RequestMapping(value = Mappings.MAX_DISTANCE_FOR_PLANE, method = RequestMethod.GET)
    public double getPlaneMaxDistance(@PathVariable(value = "plane_sid") String planeSid) {

        double reamingFuel = 0;
        double averageFuelConsumption = 0;

        //TO DO
        FlightDetails flightDetails = null;//flightDetailsService.getLatestFlightDetailsForPlane(planeSid, true);
        if (flightDetails != null) {

            reamingFuel = primitiveConverterHelperService
                    .changeDoubleObjectToPrimitive(flightDetails.getRemainingFuel());
            averageFuelConsumption = primitiveConverterHelperService
                    .changeDoubleObjectToPrimitive(flightDetails.getAverageFuelConsumption());

        }

        return maxDistanceCalculatorService.calculateMaxDistance(reamingFuel, averageFuelConsumption);

    }

}
