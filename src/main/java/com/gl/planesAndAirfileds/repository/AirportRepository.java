package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.Airport;
import org.springframework.data.repository.CrudRepository;

public interface AirportRepository extends CrudRepository<Airport,Long> {
}
