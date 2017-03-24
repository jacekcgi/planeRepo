package com.gl.planesAndAirfileds.service.impl;

import com.gl.planesAndAirfileds.domain.Airport;
import com.gl.planesAndAirfileds.repository.AbstractIdentifiableEntityRepository;
import com.gl.planesAndAirfileds.repository.AirportRepository;
import com.gl.planesAndAirfileds.service.AirportsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("airportService")
public class AirportServiceImp extends AbstractIdentifiableEntityServiceImpl<Airport> implements AirportsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AirportRepository airportRepository;

    @Override
    protected AbstractIdentifiableEntityRepository<Airport> getRepository() {
        return airportRepository;
    }

    @Autowired
    public void setAirportRepository(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    @Transactional
    public void saveAirports(List<Airport> airportsList) {

        logger.info("Saving list of airports");
        airportRepository.saveList(airportsList);
        logger.info("List of Airport saved");
    }
}