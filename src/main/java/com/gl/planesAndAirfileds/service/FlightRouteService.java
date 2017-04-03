package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.FlightRoute;
import com.gl.planesAndAirfileds.domain.dto.FlightRouteDto;

import java.util.List;

/**
 * Created by krzysztof.gonia on 3/23/2017.
 */
public interface FlightRouteService extends AbstractIdentifiableEntityService<FlightRoute> {

    List<FlightRoute> findCurrentFlights();

    FlightRoute save(FlightRouteDto flightRouteDto);
}
