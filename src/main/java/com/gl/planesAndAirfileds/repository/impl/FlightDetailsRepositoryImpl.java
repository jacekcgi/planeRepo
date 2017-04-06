package com.gl.planesAndAirfileds.repository.impl;

import com.gl.planesAndAirfileds.domain.*;
import com.gl.planesAndAirfileds.domain.dto.FlightDetailsDto;
import com.gl.planesAndAirfileds.domain.simulator.GetFlightDetailsDto;
import com.gl.planesAndAirfileds.repository.FlightDetailsRepository;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by krzysztof.gonia on 3/7/2017.
 */
@Repository("flightDetailsRepository")
public class FlightDetailsRepositoryImpl extends AbstractEntityRepositoryImpl<FlightDetails>
        implements FlightDetailsRepository {



    @Override
    public FlightDetails getLatestFlightDetails(String flightRouteSid) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<FlightDetails> criteriaQuery = builder.createQuery(FlightDetails.class);
        Root<FlightDetails> root = criteriaQuery.from(FlightDetails.class);
        Path<FlightRoute> flightRouteRoot = root.get(FlightDetails.FIELD_FLIGHT_ROUTE);

        Predicate where = builder.equal(flightRouteRoot.get(FlightRoute.FIELD_SID),flightRouteSid);
        where = builder.and(where, builder.equal(root.get(FlightDetails.FIELD_ACTUAL_POSITION), true));
        criteriaQuery.where(where);

        return getEntityManager().createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public List<FlightDetailsDto> findLatest() {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<FlightDetailsDto> criteriaQuery = builder.createQuery(FlightDetailsDto.class);
        Root<FlightDetails> root = criteriaQuery.from(FlightDetails.class);

        Path<FlightRoute> flightRouteRoot = root.get(FlightDetails.FIELD_FLIGHT_ROUTE); //default inner join
        Path<Plane> planeRoot = flightRouteRoot.get(FlightRoute.FIELD_PLANE);
        Path<Airport> airportRootDestination = flightRouteRoot.get(FlightRoute.FIELD_DESTINATION);

        LocalDateTime now = LocalDateTime.now();

        Predicate where = builder.lessThan(flightRouteRoot.get(FlightRoute.FIELD_START_DATE), now);
        where = builder
                .and(where, builder.notEqual(flightRouteRoot.get(FlightRoute.FIELD_FLIGHT_PHASE), FlightPhase.LANDED));
        where = builder.and(where, builder.equal(root.get(FlightDetails.FIELD_ACTUAL_POSITION), true));

        criteriaQuery.where(where);

        //alis change nothing but you know what binds to what
        criteriaQuery.multiselect(root.get(FlightDetails.FIELD_LATITUDE).alias(FlightDetailsDto.FIELD_CURRENT_LATITUDE),
                root.get(FlightDetails.FIELD_LONGITUDE).alias(FlightDetailsDto.FIELD_CURRENT_LONGITUDE),
                airportRootDestination.get(Airport.FIELD_LATITUDE).alias(FlightDetailsDto.FIELD_DESTINATION_LATITUDE),
                airportRootDestination.get(Airport.FIELD_LONGITUDE).alias(FlightDetailsDto.FIELD_DESTINATION_LONGITUDE),
                root.get(FlightDetails.FIELD_VELOCITY).alias(FlightDetailsDto.FIELD_VELOCITY),
                root.get(FlightDetails.FIELD_DISTANCE_TRAVELED),
                flightRouteRoot.get(FlightRoute.FIELD_FLIGHT_DISTANCE),
                flightRouteRoot.get(FlightRoute.FIELD_SID).alias(FlightDetailsDto.FIELD_FLIGHT_ROUTE_SID),
                root.get(FlightDetails.FIELD_CREATED_DATE).alias(FlightDetailsDto.FIELD_CREATED_DATE)
        );
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<GetFlightDetailsDto> findLatestForSimulator(List<FlightRoute> currentFlightRoutes) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<GetFlightDetailsDto> criteriaQuery = builder.createQuery(GetFlightDetailsDto.class);
        Root<FlightDetails> root = criteriaQuery.from(FlightDetails.class);

        Path<FlightRoute> flightRouteRoot = root.get(FlightDetails.FIELD_FLIGHT_ROUTE); //default inner join
        Path<Airport> destinationRoot = flightRouteRoot.get(FlightRoute.FIELD_DESTINATION);
        Path<Airport> sourceRoot = flightRouteRoot.get(FlightRoute.FIELD_SOURCE);

        Predicate where = builder.in(root.get(FlightDetails.FIELD_FLIGHT_ROUTE)).value(currentFlightRoutes);
        where = builder.and(where, builder.equal(root.get(FlightDetails.FIELD_ACTUAL_POSITION), true));
        criteriaQuery.where(where);

        criteriaQuery
                .multiselect(root.get(FlightDetails.FIELD_LATITUDE),
                        root.get(FlightDetails.FIELD_LONGITUDE),
                        destinationRoot.get(Airport.FIELD_LATITUDE),
                        destinationRoot.get(Airport.FIELD_LONGITUDE),
                        sourceRoot.get(Airport.FIELD_LATITUDE),
                        sourceRoot.get(Airport.FIELD_LONGITUDE),
                        flightRouteRoot.get(FlightRoute.FIELD_FLIGHT_PHASE),
                        root.get(FlightDetails.FIELD_CREATED_DATE),
                        flightRouteRoot.get(FlightRoute.FIELD_START_DATE),
                        root.get(FlightDetails.FIELD_VELOCITY),
                        root.get(FlightDetails.FIELD_DISTANCE_TRAVELED),
                        flightRouteRoot.get(FlightRoute.FIELD_FLIGHT_DISTANCE),
                        flightRouteRoot.get(FlightRoute.FIELD_SID)
                );
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    @Override
    public int updateActualPosition(List<Long> flightRouteIds) {
        Query query = getEntityManager().createNamedQuery(FlightDetails.UPDATE_ACTUAL_POSITION);
        query.setParameter("ids", flightRouteIds);
        return query.executeUpdate();
    }

    @Override
    public List<FlightDetails> findFlightGpsTrail(String flightRouteSid) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<FlightDetails> criteriaQuery = builder.createQuery(FlightDetails.class);
        Root<FlightDetails> root = criteriaQuery.from(FlightDetails.class);
        Path<FlightRoute> flightRouteRoot = root.get(FlightDetails.FIELD_FLIGHT_ROUTE);
        Predicate where = builder.equal(flightRouteRoot.get(FlightRoute.FIELD_SID),flightRouteSid);
        criteriaQuery.where(where);
        Order order = new OrderImpl(root.get(FlightDetails.FIELD_CREATED_DATE), true);
        criteriaQuery.orderBy(order);
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }
}
