package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.repository.FlightDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            logger.error("Database Exception",e);
        }
        return result;
    }

    public FlightDetails getLatestFlightDetailsForPlane(Long planeId){
        List<FlightDetails> latestFlightDetailForPlane = flightDetailsRepository.getLatestFlightDetailForPlane(planeId);

        return latestFlightDetailForPlane.get(0);
    }

    @Transactional
    public void insertNewFlightDetails(FlightDetails flightDetails) {
        List<FlightDetails> latestFlightDetails = flightDetailsRepository.getLatestFlightDetailForPlane(flightDetails.getPlane().getId());
        for(FlightDetails fd :latestFlightDetails){
            fd.setActualPosition(false);
            flightDetailsRepository.save(fd);
        }
        flightDetails.setActualPosition(true);
        flightDetailsRepository.save(flightDetails);
    }

}
