package com.gl.planesAndAirfileds.repository.impl;

import com.gl.planesAndAirfileds.domain.Airport;
import com.gl.planesAndAirfileds.repository.AirportRepository;
import com.gl.planesAndAirfileds.repository.util.JpaUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by marek.sobieraj on 2017-03-21.
 */
@Repository("airportRepository")
public class AirportRepositoryImpl extends AbstractIdentifiableEntityRepositoryImpl<Airport> implements
        AirportRepository {

    @Override
    public List<Airport> getAirportBasedOnZoomLvl(int zoomlvl) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Airport> criteria = builder.createQuery(Airport.class);
        Root<Airport> root = JpaUtils.findOrCreateRoot(criteria, Airport.class);
        criteria.where(builder.equal(
                    builder.lower(root.get(Airport.FIELD_ZOOMLEVEL)),
                    zoomlvl));

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
