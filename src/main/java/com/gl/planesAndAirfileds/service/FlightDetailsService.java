package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.FlightDetails;

import java.util.List;

/**
 * Created by marek.sroga on 2017-03-06.
 */
public interface FlightDetailsService extends AbstractEntityService<FlightDetails> {
    List<FlightDetails> getLatestFlightDetailsForPlanes(String planeId, boolean returnPlaneLanded);

    FlightDetails getLatestFlightDetailsForPlane(String planeSid, boolean returnPlaneLanded);

    void insertNewFlightDetails(FlightDetails flightDetails);
}
