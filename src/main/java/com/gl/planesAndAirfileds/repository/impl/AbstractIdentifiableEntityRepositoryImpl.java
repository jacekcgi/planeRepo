package com.gl.planesAndAirfileds.repository.impl;

import com.gl.planesAndAirfileds.domain.AbstractIdentifiableEntity;
import com.gl.planesAndAirfileds.repository.AbstractIdentifiableEntityRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by krzysztof.gonia on 3/7/2017.
 */
public abstract class AbstractIdentifiableEntityRepositoryImpl<T extends AbstractIdentifiableEntity>
        extends AbstractEntityRepositoryImpl<T> implements AbstractIdentifiableEntityRepository<T> {

    @Override
    @Transactional(readOnly = true)
    public T getBySid(String sid) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(persistentClass);
        Root<T> root = criteriaQuery.from(persistentClass);
        criteriaQuery.where(builder.equal(root.get(AbstractIdentifiableEntity.FIELD_SID), sid));
        return getEntityManager().createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findBySid(List<String> sids) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(persistentClass);
        Root<T> root = criteriaQuery.from(persistentClass);
        criteriaQuery.where(root.get(AbstractIdentifiableEntity.FIELD_SID).in(sids));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }
}
