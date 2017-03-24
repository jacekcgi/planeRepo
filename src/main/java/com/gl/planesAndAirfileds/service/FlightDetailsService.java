package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.dto.FlightDetailsDto;

import java.util.List;

/**
 * Created by marek.sroga on 2017-03-06.
 */
public interface FlightDetailsService extends AbstractEntityService<FlightDetails> {
    List<FlightDetails> getLatestFlightDetailsForPlanes(String planeId, boolean returnPlaneLanded);

    FlightDetails getLatestFlightDetailsForPlane(String planeSid, boolean returnPlaneLanded);

    List<FlightDetailsDto> findLatestFlightDetails();

//    void insertNewFlightDetails(FlightDetails flightDetails);
}
