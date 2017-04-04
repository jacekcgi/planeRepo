package com.gl.planesAndAirfileds.service.impl;

import com.gl.planesAndAirfileds.domain.*;
import com.gl.planesAndAirfileds.domain.dto.FlightRouteDto;
import com.gl.planesAndAirfileds.domain.dto.FlightRouteUpdateDto;
import com.gl.planesAndAirfileds.domain.util.GeodeticUtil;
import com.gl.planesAndAirfileds.repository.AbstractIdentifiableEntityRepository;
import com.gl.planesAndAirfileds.repository.FlightRouteRepository;
import com.gl.planesAndAirfileds.service.AirportsService;
import com.gl.planesAndAirfileds.service.FlightDetailsService;
import com.gl.planesAndAirfileds.service.FlightRouteService;
import com.gl.planesAndAirfileds.service.PlaneService;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by krzysztof.gonia on 3/23/2017.
 */
@Service("flightRouteService")
public class FlightRouteServiceImpl extends AbstractIdentifiableEntityServiceImpl<FlightRoute> implements
        FlightRouteService {

    @Autowired
    private FlightRouteRepository flightRouteRepository;

    @Autowired
    private PlaneService planeService;

    @Autowired
    private AirportsService airportsService;

    @Override
    protected AbstractIdentifiableEntityRepository<FlightRoute> getRepository() {
        return flightRouteRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FlightRoute> findCurrentFlights() {
        return flightRouteRepository.findCurrentFlights();
    }

    @Override
    @Transactional
    public FlightRoute save(FlightRouteDto flightRouteDto) {
        Plane plane = planeService.getBySid(flightRouteDto.getPlane());
        Airport source = airportsService.getBySid(flightRouteDto.getSource());
        Airport destination = airportsService.getBySid(flightRouteDto.getDestination());

        double distance = GeodeticUtil.calculateDistanceBetweenPoints(source.getLatitude(), source.getLongitude(), destination.getLatitude(), destination.getLongitude());

        FlightRoute flightRoute = new FlightRoute();
        flightRoute.setFlightDistance(distance);
        flightRoute.setStartDate(flightRouteDto.getStartDate());
        flightRoute.setIncomingDate(flightRouteDto.getIncomingDate());
        flightRoute.setPlane(plane);
        flightRoute.setSource(source);
        flightRoute.setDestination(destination);
        flightRoute.setFlightPhase(FlightPhase.READY);
        return save(flightRoute);
    }

    @Override
    @Transactional
    public FlightRoute updateDestination(FlightRoute flightRoute, Airport destination, FlightDetails flightDetails) {
        if(flightRoute != null && destination != null && flightDetails != null) {
            double distance = GeodeticUtil.calculateDistanceBetweenPoints(flightDetails.getGpsLatitude(), flightDetails.getGpsLongitude(), destination.getLatitude(), destination.getLongitude());
            flightRoute.setDestination(destination);
            flightRoute.setFlightDistance(flightRoute.getFlightDistance() + distance);
            return update(flightRoute);
        }
        return null;
    }
}
