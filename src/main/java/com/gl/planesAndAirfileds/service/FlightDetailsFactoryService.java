package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import org.springframework.stereotype.Service;

@Service
public class FlightDetailsFactoryService {

    public FlightDetails getEmptyFlightDetailsObject() {
        FlightDetails flightDetails = new FlightDetails();
        flightDetails.setId(1l);
        flightDetails.setAverageFuelConsumption(0d);
        flightDetails.setDistanceTraveled(0d);
        flightDetails.setGpsLatitude(0d);
        flightDetails.setGpsLongitude(0d);
        flightDetails.setVelocity(0f);
        flightDetails.setRemainingFuel(0d);

        return flightDetails;
    }
}
