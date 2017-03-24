package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.FlightDetails;

import java.util.List;

public interface FlightDetailsRepository extends AbstractEntityRepository<FlightDetails> {

    List<FlightDetails> getLatestFlightDetails(String planeSid, boolean returnPlaneLanded);

    List<FlightDetails> findLatest();
}
