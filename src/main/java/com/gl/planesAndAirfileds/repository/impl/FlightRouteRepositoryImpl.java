package com.gl.planesAndAirfileds.repository.impl;

import com.gl.planesAndAirfileds.domain.FlightPhase;
import com.gl.planesAndAirfileds.domain.FlightRoute;
import com.gl.planesAndAirfileds.repository.FlightRouteRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by krzysztof.gonia on 3/23/2017.
 */
@Repository("flightRouteRepository")
public class FlightRouteRepositoryImpl extends AbstractIdentifiableEntityRepositoryImpl<FlightRoute>
        implements FlightRouteRepository {

    /**
     * find all flight routes, that started and did not land yet
     *
     * @return
     */
    @Override
    public List<FlightRoute> findCurrentFlights() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<FlightRoute> criteriaQuery = builder.createQuery(FlightRoute.class);
        Root<FlightRoute> root = criteriaQuery.from(FlightRoute.class);

        LocalDateTime now = LocalDateTime.now();
        Predicate where = builder.lessThan(root.get(FlightRoute.FIELD_START_DATE), now);
        where = builder.and(where, builder.notEqual(root.get(FlightRoute.FIELD_FLIGHT_PHASE), FlightPhase.LANDED));

        criteriaQuery.where(where);

        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }
}
