package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.Airport;
import com.gl.planesAndAirfileds.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by jacekcygi on 13.03.17.
 */
public interface AirportsService {
    AirportRepository getAirportRepository();

    @Autowired
    void setAirportRepository(AirportRepository airportRepository);

    void saveAirports(List<Airport> airportsList);

    Iterable<Airport> findAirports();

    Airport getAirport(Long id);
}
