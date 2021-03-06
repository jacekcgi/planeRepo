package com.gl.planesAndAirfileds.repository.impl;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.domain.filter.Filter;
import com.gl.planesAndAirfileds.domain.filter.PlaneFilter;
import com.gl.planesAndAirfileds.repository.PlaneRepository;
import com.gl.planesAndAirfileds.repository.util.JpaUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by krzysztof.gonia on 3/6/2017.
 */
@Repository("planeRepository")
public class PlaneRepositoryImpl extends AbstractIdentifiableEntityRepositoryImpl<Plane> implements PlaneRepository {

    @Override
    public CriteriaQuery<Plane> createCriteriaFromSearchParams(Filter filter) {

        CriteriaQuery<Plane> criteria = super.createCriteriaFromSearchParams(filter);
        Root<Plane> root = JpaUtils.findOrCreateRoot(criteria, Plane.class);
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();

        Predicate where = builder.conjunction();

        if (filter != null && PlaneFilter.class.isAssignableFrom(filter.getClass())) {
            PlaneFilter planeFilter = (PlaneFilter) filter;

            if (StringUtils.isNotBlank(planeFilter.getName())) {
                where = builder.and(where, builder.like(
                        builder.lower(root.get(Plane.FIELD_NAME)),
                        "%" + planeFilter.getName().toLowerCase() + "%"));
            }

            criteria.where(where);
        }

        return criteria;
    }

    @Override
    public List<String> findPlanesSid() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);

        Root<Plane> root = criteriaQuery.from(Plane.class);
        criteriaQuery.select(root.get(Plane.FIELD_SID));

        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Plane> findAllContainsDescription(String description) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Plane> criteriaQuery = builder.createQuery(Plane.class);
        Root<Plane> root = criteriaQuery.from(Plane.class);

        criteriaQuery.where(builder.like(root.get(Plane.FIELD_DESCRIPTION), "%" + description + "%"));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    @Override
    public long countByRegistration(String registration, String ignoreSid) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Plane> root = criteriaQuery.from(Plane.class);
        criteriaQuery.select(builder.count(root));

        Predicate where = builder
                .equal(builder.lower(root.get(Plane.FIELD_REGISTRATION)), StringUtils.lowerCase(registration));
        if (!StringUtils.isBlank(ignoreSid)) {
            where = builder.and(where, builder.notEqual(root.get(Plane.FIELD_SID), ignoreSid));
        }
        criteriaQuery.where(where);

        return getEntityManager().createQuery(criteriaQuery).getSingleResult();
    }
}
