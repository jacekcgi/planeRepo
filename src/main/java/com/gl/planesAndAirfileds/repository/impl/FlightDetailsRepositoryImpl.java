package com.gl.planesAndAirfileds.repository.impl;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.repository.FlightDetailsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by krzysztof.gonia on 3/7/2017.
 */
@Repository("flightDetailsRepository")
public class FlightDetailsRepositoryImpl extends AbstractEntityRepositoryImpl<FlightDetails>
        implements FlightDetailsRepository {

    /**
     * Returns flight details where field isActualPosition = true
     * @param planeSid can be null, if null flight details of all planes are returned
     * @param returnPlaneLanded if true also the planes the landed are returned, if false - planes that landed are not returned
     * @return
     */
    @Override
    public List<FlightDetails> getLatestFlightDetails(String planeSid, boolean returnPlaneLanded) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<FlightDetails> criteriaQuery = builder.createQuery(FlightDetails.class);
        Root<FlightDetails> root = criteriaQuery.from(FlightDetails.class);

        Predicate where = builder.equal(root.get(FlightDetails.FIELD_IS_ACTUAL_POSITION), true);
        if (!StringUtils.isBlank(planeSid)) {
            where = builder.and(where, builder.equal(root.get(FlightDetails.FIELD_PLANE).get(Plane.FIELD_SID), planeSid));
        }
        if (!returnPlaneLanded) {
            where = builder.and(where, builder.equal(root.get(FlightDetails.FIELD_IS_LANDED), false));
        }
        criteriaQuery.where(where);
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }
}
