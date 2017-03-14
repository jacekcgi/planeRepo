package com.gl.planesAndAirfileds.service.impl;

import com.gl.planesAndAirfileds.domain.Airport;
import com.gl.planesAndAirfileds.repository.AirportRepository;
import com.gl.planesAndAirfileds.service.AirportsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportsServiceImp implements AirportsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AirportRepository airportRepository;

    @Override
    public AirportRepository getAirportRepository() {
        return airportRepository;
    }

    @Override
    @Autowired
    public void setAirportRepository(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public void saveAirports(List<Airport> airportsList) {

        logger.info("Saving list of airports");
        airportRepository.save(airportsList);
        logger.info("List of Airport saved");
    }

    @Override
    public Iterable<Airport> findAirports() {

        return airportRepository.findAll();
    }

    @Override
    public Airport getAirport(Long id) {

        return airportRepository.findOne(id);
    }

}
