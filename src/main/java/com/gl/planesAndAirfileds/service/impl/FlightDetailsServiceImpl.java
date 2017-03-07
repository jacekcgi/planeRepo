package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.domain.filter.Filter;
import com.gl.planesAndAirfileds.repository.AbstractEntityRepository;
import com.gl.planesAndAirfileds.repository.FlightDetailsRepository;
import com.gl.planesAndAirfileds.service.FlightDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FlightDetailsServiceImpl extends AbstractEntityServiceImpl<FlightDetails, Long> implements FlightDetailsService
{
    private FlightDetailsRepository flightDetailsRepository;

    @Autowired
    public FlightDetailsServiceImpl(FlightDetailsRepository flightDetailsRepository) {
        this.flightDetailsRepository = flightDetailsRepository;
    }

    @Override
    protected AbstractEntityRepository<FlightDetails, Long> getRepository() {
        return flightDetailsRepository;
    }

    @Override
    public List<FlightDetails> findBySearchParams(Filter filter, PageRequest pageRequest) {
        return flightDetailsRepository.findBySearchParams(filter, pageRequest);
    }

    @Override
    public long countBySearchParams(Filter filter) {
        return flightDetailsRepository.countBySearchParams(filter);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FlightDetails> getLatestFlightDetailsForPlanes(Long planeId){
        if (planeId == null) {
            return flightDetailsRepository.getLatestFlightDetailsForAllPlanes();
        } else {
            return flightDetailsRepository.getLatestFlightDetailForPlane(planeId);
        }
    }

    @Override
    @Transactional(readOnly = true)
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
