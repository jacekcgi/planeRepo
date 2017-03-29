package com.gl.planesAndAirfileds.service.impl;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.domain.FlightPhase;
import com.gl.planesAndAirfileds.domain.FlightRoute;
import com.gl.planesAndAirfileds.domain.dto.FlightDetailsDto;
import com.gl.planesAndAirfileds.domain.filter.Filter;
import com.gl.planesAndAirfileds.domain.simulator.GetFlightDetailsDto;
import com.gl.planesAndAirfileds.domain.simulator.PostFlightDetailsDto;
import com.gl.planesAndAirfileds.exceptions.InsertFlightDetailsException;
import com.gl.planesAndAirfileds.repository.AbstractEntityRepository;
import com.gl.planesAndAirfileds.repository.FlightDetailsRepository;
import com.gl.planesAndAirfileds.service.FlightDetailsService;
import com.gl.planesAndAirfileds.service.FlightRouteService;
import com.gl.planesAndAirfileds.service.PlaneService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FlightDetailsServiceImpl extends AbstractEntityServiceImpl<FlightDetails>
        implements FlightDetailsService {

    private FlightDetailsRepository flightDetailsRepository;

    private PlaneService planeService;

    private FlightDetailsFactoryService flightDetailsFactoryService;

    private FlightRouteService flightRouteService;

    @Autowired
    public FlightDetailsServiceImpl(FlightDetailsRepository flightDetailsRepository, PlaneService planeService,
                                    FlightRouteService flightRouteService) {
        this.flightDetailsRepository = flightDetailsRepository;
        this.planeService = planeService;
        this.flightRouteService = flightRouteService;
    }

    @Override
    protected AbstractEntityRepository<FlightDetails> getRepository() {
        return flightDetailsRepository;
    }

    @Override
    public List<FlightDetails> findBySearchParams(Filter filter, Pageable pageRequest) {
        return flightDetailsRepository.findBySearchParams(filter, pageRequest);
    }

    @Override
    public long countBySearchParams(Filter filter) {
        return flightDetailsRepository.countBySearchParams(filter);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FlightDetails> getLatestFlightDetailsForPlanes(String planeSid, boolean returnPlaneLanded) {
        return flightDetailsRepository.getLatestFlightDetails(planeSid, returnPlaneLanded);
    }

    @Override
    @Transactional(readOnly = true)
    public FlightDetails getLatestFlightDetailsForPlane(String planeSid, boolean returnPlaneLanded) {
        List<FlightDetails> latestFlightDetailForPlane = flightDetailsRepository
                .getLatestFlightDetails(planeSid, returnPlaneLanded);

        if (latestFlightDetailForPlane.isEmpty()) {

            return null;
        }

        return latestFlightDetailForPlane.get(0);

    }

    @Override
    @Transactional(readOnly = true)
    public List<FlightDetailsDto> findLatestFlightDetails() {
        List<FlightDetailsDto> flightDetailsDtos = flightDetailsRepository.findLatest();
        LocalDateTime now = LocalDateTime.now();
        flightDetailsDtos.forEach(flightDetailsDto ->
        {
            flightDetailsDto.setTimeElapsed(ChronoUnit.MILLIS.between(flightDetailsDto.getCreated(), now));
        });
        return flightDetailsDtos;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GetFlightDetailsDto> findLatestForSimulator() {
        List<FlightRoute> currentFlightRoutes = flightRouteService.findCurrentFlights();

        List<FlightRoute> planesInAir = new ArrayList<>();
        List<GetFlightDetailsDto> getFlightDetailsDtos = new ArrayList<>();
        for (FlightRoute fr : currentFlightRoutes) {
            if (fr.getFlightPhase() == FlightPhase.READY) {
                getFlightDetailsDtos.add(new GetFlightDetailsDto(fr));
            }
            else {
                planesInAir.add(fr);
            }
        }
        if (CollectionUtils.isNotEmpty(planesInAir)) {
            List<GetFlightDetailsDto> currentlyFlying = flightDetailsRepository.findLatestForSimulator(planesInAir);
            getFlightDetailsDtos.addAll(currentlyFlying);
        }
        return getFlightDetailsDtos;
    }

    @Override
    @Transactional
    public void insertNewFlightDetails(List<PostFlightDetailsDto> flightDetailsDtos) {
        if (CollectionUtils.isNotEmpty(flightDetailsDtos)) {
            Set<String> flightRouteSids = flightDetailsDtos.stream().map(PostFlightDetailsDto::getFlightRouteSid)
                    .collect(Collectors.toSet());

            List<FlightRoute> flightRoutes = flightRouteService.findBySid(new ArrayList<>(flightRouteSids));
            List<Long> flightRouteIds = flightRoutes.stream().map(FlightRoute::getId).collect(Collectors.toList());
            flightDetailsRepository.updateActualPosition(flightRouteIds);

            List<FlightDetails> flightDetailsToSave = new ArrayList<>();
            for (PostFlightDetailsDto dto : flightDetailsDtos) {
                FlightDetails flightDetails = new FlightDetails();
                BeanUtils.copyProperties(dto, flightDetails, PostFlightDetailsDto.FIELD_FLIGHT_PHASE,
                        PostFlightDetailsDto.FIELD_FLIGHT_ROUTE_SID);
                flightDetails.setActualPosition(true);
                flightDetails.setCreatedDate(LocalDateTime.now());
                FlightRoute flightRoute = flightRoutes.stream()
                        .filter(fr -> fr.getSid().equals(dto.getFlightRouteSid())).findFirst()
                        .orElseThrow(() -> new InsertFlightDetailsException(
                                "The given flight route with sid:  " + dto.getFlightRouteSid() + " does not exist!"));
                flightDetails.setFlightRoute(flightRoute);
                flightDetailsToSave.add(flightDetails);

//                update if flight phase has been changed
                if (dto.getFlightPhase() != flightRoute.getFlightPhase()) {
                    if (dto.getFlightPhase() == FlightPhase.LANDED) {
                        flightRoute.setLandedDate(LocalDateTime.now());
                    }
                    flightRoute.setFlightPhase(dto.getFlightPhase());
                    flightRouteService.update(flightRoute);
                }
            }
            saveList(flightDetailsToSave);
        }
    }

}
