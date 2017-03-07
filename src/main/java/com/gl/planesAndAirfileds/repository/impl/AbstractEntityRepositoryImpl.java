package com.gl.planesAndAirfileds.repository.impl;

import com.gl.planesAndAirfileds.domain.AbstractEntity;
import com.gl.planesAndAirfileds.domain.filter.Filter;
import com.gl.planesAndAirfileds.repository.CustomAbstractEntityRepository;
import com.gl.planesAndAirfileds.repository.util.JpaUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by krzysztof.gonia on 3/7/2017.
 */
@Component
public abstract class AbstractEntityRepositoryImpl<T extends AbstractEntity> implements CustomAbstractEntityRepository<T> {

    private final Class<T> persistentClass;

    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public AbstractEntityRepositoryImpl() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Class<T> getDomainClass() {
        return persistentClass;
    }

    @Override
    public List<T> findBySearchParams(Filter filter, PageRequest pageRequest) {
        CriteriaQuery criteria = createCriteriaFromSearchParams(filter);
        addOrder(criteria, pageRequest.getSort());
        return applyPaging(criteria, pageRequest.getOffset(), pageRequest.getPageSize()).getResultList();
    }

    /**
     * Method add order to criteria.
     * <b>TODO CURRENT IMPLEMENTATION DON'T WORK WITH NESTED ENTITY</b>
     *
     * @param criteria Criteria on which we apply sort
     * @param sort sort
     */
    protected void addOrder(CriteriaQuery criteria, Sort sort) {
        if(sort != null) {
            Iterator<Sort.Order> iterator = sort.iterator();
            List<Order> orders = new ArrayList<>();
            while (iterator.hasNext()) {
                orders.add(getOrder(criteria, iterator.next()));
            }
            criteria.orderBy(orders);
        }
    }

    @Override
    public long countBySearchParams(Filter filter)
    {
        CriteriaQuery criteria = createCriteriaFromSearchParams(filter);
        if (criteria.getRoots().isEmpty())
        {
            criteria.from(getDomainClass());
        }
        return JpaUtils.count(getEntityManager(), criteria);
    }

    private Order getOrder(CriteriaQuery criteria, Sort.Order order) {
        Root root = JpaUtils.findOrCreateRoot(criteria, getDomainClass());
        return order.isAscending() ?
                getEntityManager().getCriteriaBuilder().asc(root.get(order.getProperty())) :
                getEntityManager().getCriteriaBuilder().desc(root.get(order.getProperty()));
    }

    protected TypedQuery applyPaging(CriteriaQuery criteria, int offset, int limit) {
        return getEntityManager().createQuery(criteria).setFirstResult(offset).setMaxResults(limit);
    }

    public CriteriaQuery<T> createCriteriaFromSearchParams(Filter filter) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        return builder.createQuery(getDomainClass());
    }
}
