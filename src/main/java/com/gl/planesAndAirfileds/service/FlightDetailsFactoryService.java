package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import org.springframework.stereotype.Service;

@Service
public class FlightDetailsFactoryService {

    public FlightDetails getEmptyFlightDetailsObject() {
        FlightDetails flightDetails = new FlightDetails();
        flightDetails.setId(1l);
        flightDetails.setActualPosition(false);
        flightDetails.setAverageFuelConsumption(0d);
        flightDetails.setCourse(0d);
        flightDetails.setDistanceTraveled(0d);
        flightDetails.setFlightDistance(0d);
        flightDetails.setGpsLatitude(0d);
        flightDetails.setGpsLongitude(0d);
        flightDetails.setFlightTime(0l);
        flightDetails.setIncomingTime(null);
        flightDetails.setVelocity(0f);
        flightDetails.setRemainingFuel(0d);

        return flightDetails;
    }
}
