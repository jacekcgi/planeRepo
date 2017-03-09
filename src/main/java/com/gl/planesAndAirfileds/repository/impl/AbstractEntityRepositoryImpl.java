package com.gl.planesAndAirfileds.repository.impl;

import com.gl.planesAndAirfileds.domain.AbstractEntity;
import com.gl.planesAndAirfileds.domain.filter.Filter;
import com.gl.planesAndAirfileds.repository.CustomAbstractEntityRepository;
import com.gl.planesAndAirfileds.repository.util.JpaUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

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
    public List<T> findBySearchParams(Filter filter, Pageable pageRequest) {
        CriteriaQuery criteria = createCriteriaFromSearchParams(filter);
        addOrder(criteria, pageRequest.getSort());
        return applyPaging(criteria, pageRequest.getOffset(), pageRequest.getPageSize()).getResultList();
    }

    /**
     * Method add order to criteria.
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

    private Order getOrder(CriteriaQuery criteria, Sort.Order order) {
        Path path = JpaUtils.findOrCreateRoot(criteria, getDomainClass());

        StringTokenizer stringTokenizer = new StringTokenizer(order.getProperty(), ".");
        while (stringTokenizer.hasMoreTokens())
        {
            path = path.get(stringTokenizer.nextToken());
        }

        return order.isAscending() ?
                getEntityManager().getCriteriaBuilder().asc(path) :
                getEntityManager().getCriteriaBuilder().desc(path);
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

    protected TypedQuery applyPaging(CriteriaQuery criteria, int offset, int limit) {
        return getEntityManager().createQuery(criteria).setFirstResult(offset).setMaxResults(limit);
    }

    public CriteriaQuery<T> createCriteriaFromSearchParams(Filter filter) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        return builder.createQuery(getDomainClass());
    }
}
