package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.repository.FlightDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightDetailsDAOService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private FlightDetailsRepository flightDetailsRepository;

    @Autowired
    public FlightDetailsDAOService(FlightDetailsRepository flightDetailsRepository) {
        this.flightDetailsRepository = flightDetailsRepository;
    }

    public List<FlightDetails> getLatestFlightDetailsForPlanes(Long planeId){
        List<FlightDetails> result = null;
        try {
            if(planeId == null) {
                result = flightDetailsRepository.getLatestFlightDetailsForAllPlanes();
            }else {
                result = flightDetailsRepository.getLatestFlightDetailForPlane(planeId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return result;
    }

    public FlightDetails getLatestFlightDetailsForPlane(Long planeId){
        List<FlightDetails> latestFlightDetailForPlane = flightDetailsRepository.getLatestFlightDetailForPlane(planeId);

        return latestFlightDetailForPlane.get(0);
    }

}
