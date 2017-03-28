package com.gl.planesAndAirfileds.service.impl;

import com.gl.planesAndAirfileds.domain.Airport;
import com.gl.planesAndAirfileds.domain.filter.Filter;
import com.gl.planesAndAirfileds.repository.AbstractIdentifiableEntityRepository;
import com.gl.planesAndAirfileds.repository.AirportRepository;
import com.gl.planesAndAirfileds.service.AirportsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Airport> findBySearchParams(Filter filter, Pageable pageRequest) {
        return airportRepository.findBySearchParams(filter, pageRequest);
    }

    @Override
    public long countBySearchParams(Filter filter) {
        return airportRepository.countBySearchParams(filter);
    }


    @Override
    @Transactional
    public void saveAirports(List<Airport> airportsList) {

        logger.info("Saving list of airports");
        airportRepository.saveList(airportsList);
        logger.info("List of Airport saved");
    }
}
