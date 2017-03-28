package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.FlightRoute;

import java.util.List;

/**
 * Created by krzysztof.gonia on 3/23/2017.
 */
public interface FlightRouteRepository extends AbstractIdentifiableEntityRepository<FlightRoute> {

    List<FlightRoute> findCurrentFlights();
}
