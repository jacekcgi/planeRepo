package com.gl.planesAndAirfileds.service.impl;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.domain.filter.Filter;
import com.gl.planesAndAirfileds.repository.AbstractEntityRepository;
import com.gl.planesAndAirfileds.repository.FlightDetailsRepository;
import com.gl.planesAndAirfileds.service.FlightDetailsFactoryService;
import com.gl.planesAndAirfileds.service.FlightDetailsService;
import com.gl.planesAndAirfileds.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FlightDetailsServiceImpl extends AbstractEntityServiceImpl<FlightDetails, Long>
        implements FlightDetailsService {
    private FlightDetailsRepository flightDetailsRepository;

    private PlaneService planeService;

    private FlightDetailsFactoryService flightDetailsFactoryService;

    @Autowired
    public FlightDetailsServiceImpl(FlightDetailsRepository flightDetailsRepository, PlaneService planeService) {
        this.flightDetailsRepository = flightDetailsRepository;
        this.planeService = planeService;
    }

    public FlightDetailsFactoryService getFlightDetailsFactoryService() {
        return flightDetailsFactoryService;
    }

    @Autowired
    public void setFlightDetailsFactoryService(FlightDetailsFactoryService flightDetailsFactoryService) {
        this.flightDetailsFactoryService = flightDetailsFactoryService;
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
    public List<FlightDetails> getLatestFlightDetailsForPlanes(String planeSid) {
        if (planeSid == null) {
            return flightDetailsRepository.getLatestFlightDetailsForAllPlanes();
        }
        else {
            return flightDetailsRepository.getLatestFlightDetailForPlane(planeSid);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public FlightDetails getLatestFlightDetailsForPlane(String planeSid) {
        List<FlightDetails> latestFlightDetailForPlane = flightDetailsRepository
                .getLatestFlightDetailForPlane(planeSid);

        if (latestFlightDetailForPlane.isEmpty()) {

            return flightDetailsFactoryService.getEmptyFlightDetailsObject();
        }

        return latestFlightDetailForPlane.get(0);

    }

    @Override
    @Transactional
    public void insertNewFlightDetails(FlightDetails flightDetails) {
        Plane plane = planeService.getBySid(flightDetails.getPlane().getSid());
        if (plane != null) {
            List<FlightDetails> latestFlightDetails = flightDetailsRepository
                    .getLatestFlightDetailForPlane(plane.getSid());
            for (FlightDetails fd : latestFlightDetails) {
                fd.setActualPosition(false);
                flightDetailsRepository.save(fd);
            }
            flightDetails.setPlane(plane);
            flightDetails.setActualPosition(true);
            flightDetailsRepository.save(flightDetails);
        }
    }

}
