package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.FlightDetails;

import java.util.List;

/**
 * Created by marek.sroga on 2017-03-06.
 */
public interface FlightDetailsService extends AbstractEntityService<FlightDetails, Long>
{
    List<FlightDetails> getLatestFlightDetailsForPlanes(Long planeId);

    FlightDetails getLatestFlightDetailsForPlane(Long planeId);
}
