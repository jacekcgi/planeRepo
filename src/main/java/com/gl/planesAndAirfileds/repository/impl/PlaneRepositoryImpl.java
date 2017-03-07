package com.gl.planesAndAirfileds.repository.impl;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.repository.CustomPlaneRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by krzysztof.gonia on 3/6/2017.
 */
@Repository
public class PlaneRepositoryImpl implements CustomPlaneRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Plane> findAllContainsDescription(String description)
    {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Plane> criteriaQuery = builder.createQuery(Plane.class);
        Root<Plane> root = criteriaQuery.from(Plane.class);

        criteriaQuery.where(builder.like(root.get(Plane.FIELD_DESCRIPTION),"%" + description + "%"));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public long countByRegistration(String registration, String ignoreSid)
    {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root root = criteriaQuery.from(Plane.class);
        criteriaQuery.select(builder.count(root));

        Predicate where = builder.equal(builder.lower(root.get(Plane.FIELD_REGISTRATION)), StringUtils.lowerCase(registration));
        if (!StringUtils.isBlank(ignoreSid))
        {
            where = builder.and(where, builder.notEqual(root.get(Plane.FIELD_SID), ignoreSid));

        }
        criteriaQuery.where(where);

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
