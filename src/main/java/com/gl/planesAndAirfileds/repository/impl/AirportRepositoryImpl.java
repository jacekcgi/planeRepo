package com.gl.planesAndAirfileds.repository.impl;

import com.gl.planesAndAirfileds.domain.Airport;
import com.gl.planesAndAirfileds.domain.filter.AirportFilter;
import com.gl.planesAndAirfileds.domain.filter.Filter;
import com.gl.planesAndAirfileds.repository.AirportRepository;
import com.gl.planesAndAirfileds.repository.util.JpaUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by marek.sobieraj on 2017-03-21.
 */
@Repository("airportRepository")
public class AirportRepositoryImpl extends AbstractIdentifiableEntityRepositoryImpl<Airport> implements
        AirportRepository {

    @Override
    public CriteriaQuery<Airport> createCriteriaFromSearchParams(Filter filter) {

        CriteriaQuery<Airport> criteria = super.createCriteriaFromSearchParams(filter);
        Root<Airport> root = JpaUtils.findOrCreateRoot(criteria, Airport.class);
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();

        Predicate where = builder.conjunction();

        if (filter != null && AirportFilter.class.isAssignableFrom(filter.getClass())) {
            AirportFilter airportFilter = (AirportFilter) filter;

            if (StringUtils.isNotBlank(airportFilter.getName())) {
                where = builder.and(where, builder.like(
                        builder.lower(root.get(Airport.FIELD_NAME)),
                        "%" + airportFilter.getName().toLowerCase() + "%"));
            }

            criteria.where(where);
        }

        return criteria;
    }
}
