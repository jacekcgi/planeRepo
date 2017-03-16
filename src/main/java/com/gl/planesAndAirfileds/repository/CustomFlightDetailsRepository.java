package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.FlightDetails;

import java.util.List;

public interface CustomFlightDetailsRepository extends CustomAbstractEntityRepository<FlightDetails> {

    List<FlightDetails> getLatestFlightDetails(String planeSid, boolean returnPlaneLanded);
}
