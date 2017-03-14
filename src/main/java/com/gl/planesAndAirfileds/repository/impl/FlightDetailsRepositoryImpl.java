package com.gl.planesAndAirfileds.repository.impl;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.repository.CustomFlightDetailsRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by krzysztof.gonia on 3/7/2017.
 */
@Repository
public class FlightDetailsRepositoryImpl extends AbstractEntityRepositoryImpl<FlightDetails>
        implements CustomFlightDetailsRepository {

}
