package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.Airport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends CrudRepository<Airport, Long> {
}
