package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.Airports;
import com.gl.planesAndAirfileds.repository.AirportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportDAOService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AirportRepository airportRepository;

    public AirportRepository getAirportRepository() {
        return airportRepository;
    }

    @Autowired
    public void setAirportRepository(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public void saveAirports(List<Airports> airportsList) {

        logger.info("Saving list of airports");
        airportRepository.save(airportsList);
        logger.info("List of Airports saved");
    }
}
