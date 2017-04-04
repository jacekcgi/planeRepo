package com.gl.planesAndAirfileds.service.impl;

import com.gl.planesAndAirfileds.domain.Airport;
import com.gl.planesAndAirfileds.repository.AbstractIdentifiableEntityRepository;
import com.gl.planesAndAirfileds.repository.AirportRepository;
import com.gl.planesAndAirfileds.service.AirportsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("airportService")
public class AirportServiceImp extends AbstractIdentifiableEntityServiceImpl<Airport> implements AirportsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AirportRepository airportRepository;

    @Autowired
    public AirportServiceImp(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    protected AbstractIdentifiableEntityRepository<Airport> getRepository() {
        return airportRepository;
    }

    @Override
    public List<Airport> getAirportOnZoomLvl(int zoomLvl) {
        return airportRepository.getAirportBasedOnZoomLvl(zoomLvl);
    }

}

