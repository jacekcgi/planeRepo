package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.Airport;

import java.util.List;

public interface AirportRepository extends AbstractIdentifiableEntityRepository<Airport> {

    List<Airport> getAirportBasedOnZoomLvl(int zoomlvl);
}
