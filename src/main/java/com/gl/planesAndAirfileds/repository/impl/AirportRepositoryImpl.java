package com.gl.planesAndAirfileds.repository.impl;

import com.gl.planesAndAirfileds.domain.Airport;
import com.gl.planesAndAirfileds.repository.AirportRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by marek.sobieraj on 2017-03-21.
 */
@Repository("airportRepository")
public class AirportRepositoryImpl extends AbstractIdentifiableEntityRepositoryImpl<Airport> implements
        AirportRepository {
}
