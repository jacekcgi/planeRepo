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

    private FlightDetailsRepository flightDetailsRepository;

    @Autowired
    public FlightDetailsDAOService(FlightDetailsRepository flightDetailsRepository) {
        this.flightDetailsRepository = flightDetailsRepository;
    }

    public List<FlightDetails> getLatestFlightDetailsForPlanes(Long planeId){
        if (planeId == null) {
            return flightDetailsRepository.getLatestFlightDetailsForAllPlanes();
        } else {
            return flightDetailsRepository.getLatestFlightDetailForPlane(planeId);
        }
    }

    public FlightDetails getLatestFlightDetailsForPlane(Long planeId){
        List<FlightDetails> latestFlightDetailForPlane = flightDetailsRepository.getLatestFlightDetailForPlane(planeId);

        return latestFlightDetailForPlane.get(0);
    }

    @Transactional()
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
