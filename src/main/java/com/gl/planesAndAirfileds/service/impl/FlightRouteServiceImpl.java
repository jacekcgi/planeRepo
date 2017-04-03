package com.gl.planesAndAirfileds.service.impl;

import com.gl.planesAndAirfileds.domain.Airport;
import com.gl.planesAndAirfileds.domain.FlightPhase;
import com.gl.planesAndAirfileds.domain.FlightRoute;
import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.domain.dto.FlightRouteDto;
import com.gl.planesAndAirfileds.repository.AbstractIdentifiableEntityRepository;
import com.gl.planesAndAirfileds.repository.FlightRouteRepository;
import com.gl.planesAndAirfileds.service.AirportsService;
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

        GeodeticCalculator geoCalc = new GeodeticCalculator();
        Ellipsoid reference = Ellipsoid.WGS84;
        GlobalPosition pointA = new GlobalPosition(source.getLatitude(), source.getLongitude(), 0.0);
        GlobalPosition userPos = new GlobalPosition(destination.getLatitude(), destination.getLongitude(), 0.0);

        double distance = geoCalc.calculateGeodeticCurve(reference, userPos, pointA).getEllipsoidalDistance();

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
    public FlightRoute save(FlightRoute entity) {
        return super.save(entity);
    }
}
