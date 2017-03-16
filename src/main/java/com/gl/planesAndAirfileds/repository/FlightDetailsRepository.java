package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.FlightDetails;

public interface FlightDetailsRepository
        extends AbstractEntityRepository<FlightDetails, Long>, CustomFlightDetailsRepository {

}
