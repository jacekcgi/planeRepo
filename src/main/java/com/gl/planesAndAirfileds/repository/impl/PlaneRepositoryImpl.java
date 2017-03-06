package com.gl.planesAndAirfileds.repository.impl;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.repository.CustomPlaneRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
    @Transactional(readOnly = true)
    public List<Plane> findAllContainsDescription(String description)
    {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Plane> criteriaQuery = builder.createQuery(Plane.class);
        Root<Plane> root = criteriaQuery.from(Plane.class);

        criteriaQuery.where(builder.like(root.get(Plane.FIELD_DESCRIPTION),"%" + description + "%"));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
