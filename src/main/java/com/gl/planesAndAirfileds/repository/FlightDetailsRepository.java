package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.domain.FlightRoute;
import com.gl.planesAndAirfileds.domain.dto.FlightDetailsDto;
import com.gl.planesAndAirfileds.domain.simulator.GetFlightDetailsDto;

import java.util.List;

public interface FlightDetailsRepository extends AbstractEntityRepository<FlightDetails> {

    List<FlightDetailsDto> findLatest();

    List<GetFlightDetailsDto> findLatestForSimulator(List<FlightRoute> currentFlightRoutes);

    int updateActualPosition(List<Long> flightRouteIds);

    FlightDetails getLatestFlightDetails(String flightRouteSid);

    List<FlightDetails> findFlightGpsTrail(String flightSid);
}
