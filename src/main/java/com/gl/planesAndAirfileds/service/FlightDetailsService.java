package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.domain.dto.FlightDetailsDto;
import com.gl.planesAndAirfileds.domain.simulator.GetFlightDetailsDto;
import com.gl.planesAndAirfileds.domain.simulator.PostFlightDetailsDto;

import java.util.List;

/**
 * Created by marek.sroga on 2017-03-06.
 */
public interface FlightDetailsService extends AbstractEntityService<FlightDetails> {

    List<FlightDetailsDto> findLatestFlightDetails();

    void insertNewFlightDetails(List<PostFlightDetailsDto> flightDetails);

    List<GetFlightDetailsDto> findLatestForSimulator();

    FlightDetails getFlightDetailsByFlightRoute(String flightRouteSid);

    List<FlightDetails> findFlightTrail(String flightRouteSid);
}
