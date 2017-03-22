package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.Airport;

import java.util.List;

/**
 * Created by jacekcygi on 13.03.17.
 */
public interface AirportsService extends AbstractIdentifiableEntityService<Airport> {
    void saveAirports(List<Airport> airportsList);
}
