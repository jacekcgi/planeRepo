package com.gl.planesAndAirfileds.service.impl;

import com.gl.planesAndAirfileds.domain.FlightRoute;
import com.gl.planesAndAirfileds.repository.AbstractIdentifiableEntityRepository;
import com.gl.planesAndAirfileds.repository.FlightRouteRepository;
import com.gl.planesAndAirfileds.service.FlightRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by krzysztof.gonia on 3/23/2017.
 */
@Service("flightRouteService")
public class FlightRouteServiceImpl extends AbstractIdentifiableEntityServiceImpl<FlightRoute> implements
        FlightRouteService {

    @Autowired
    private FlightRouteRepository flightRouteRepository;

    @Override
    protected AbstractIdentifiableEntityRepository<FlightRoute> getRepository() {
        return flightRouteRepository;
    }
}