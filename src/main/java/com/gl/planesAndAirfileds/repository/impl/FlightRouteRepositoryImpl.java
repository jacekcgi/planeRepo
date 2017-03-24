package com.gl.planesAndAirfileds.repository.impl;

import com.gl.planesAndAirfileds.domain.FlightRoute;
import com.gl.planesAndAirfileds.repository.FlightRouteRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by krzysztof.gonia on 3/23/2017.
 */
@Repository("flightRouteRepository")
public class FlightRouteRepositoryImpl extends AbstractIdentifiableEntityRepositoryImpl<FlightRoute> implements FlightRouteRepository {
}
