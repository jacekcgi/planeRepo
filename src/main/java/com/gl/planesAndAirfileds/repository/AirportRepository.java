package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.Airports;
import org.springframework.data.repository.CrudRepository;

public interface AirportRepository extends CrudRepository<Airports,Long> {
}
