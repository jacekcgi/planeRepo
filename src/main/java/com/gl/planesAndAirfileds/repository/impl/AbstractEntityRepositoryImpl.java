package com.gl.planesAndAirfileds.repository.impl;

import com.gl.planesAndAirfileds.domain.AbstractEntity;
import com.gl.planesAndAirfileds.domain.filter.Filter;
import com.gl.planesAndAirfileds.repository.AbstractEntityRepository;
import com.gl.planesAndAirfileds.repository.util.JpaUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * Created by krzysztof.gonia on 3/7/2017.
 */
public abstract class AbstractEntityRepositoryImpl<T extends AbstractEntity>
        implements AbstractEntityRepository<T> {

    protected final Class<T> persistentClass;

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
        CriteriaQuery<T> criteria = createCriteriaFromSearchParams(filter);
        addOrder(criteria, pageRequest.getSort());
        if (CollectionUtils.isEmpty(criteria.getRoots())) {
            criteria.from(getDomainClass());
        }
        return applyPaging(criteria, pageRequest.getOffset(), pageRequest.getPageSize()).getResultList();
    }

    /**
     * Method add order to criteria.
     *
     * @param criteria Criteria on which we apply sort
     * @param sort     sort
     */
    protected void addOrder(CriteriaQuery criteria, Sort sort) {
        if (sort != null) {
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
        while (stringTokenizer.hasMoreTokens()) {
            path = path.get(stringTokenizer.nextToken());
        }

        return order.isAscending() ?
                getEntityManager().getCriteriaBuilder().asc(path) :
                getEntityManager().getCriteriaBuilder().desc(path);
    }

    @Override
    public long countBySearchParams(Filter filter) {
        CriteriaQuery<T> criteria = createCriteriaFromSearchParams(filter);
        if (criteria.getRoots().isEmpty()) {
            criteria.from(getDomainClass());
        }
        return JpaUtils.count(getEntityManager(), criteria);
    }

    @Override
    public T save(T entity) {
        getEntityManager().persist(entity);
        return entity;
    }

    @Override
    public void saveList(Collection<T> entities) {
        if (CollectionUtils.isNotEmpty(entities)) {
            for (T entity : entities) {
                getEntityManager().persist(entity);
            }
        }
    }

    @Override
    public void delete(T entity) {
        getEntityManager().remove(entity);
    }

    @Override
    public void deleteList(Collection<T> entities) {
        if (CollectionUtils.isNotEmpty(entities)) {
            for (T entity : entities) {
                delete(entity);
            }
        }
    }

    @Override
    public T getById(Long id) {
        return getEntityManager().find(persistentClass, id);
    }

    @Override
    public List<T> findAll(String orderBy, boolean ascending) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(persistentClass);
        Root<T> root = criteria.from(persistentClass);
        if (StringUtils.isNotBlank(orderBy)) {
            Order order = new OrderImpl(getAliasOrder(orderBy, root), ascending);
            criteria.orderBy(order);
        }
        return getEntityManager().createQuery(criteria).getResultList();
    }

    private static final Expression getAliasOrder(String field, Root root) {
        StringTokenizer tokenizer = new StringTokenizer(field, ".");
        return getNextOrderExpression(tokenizer, root);
    }

    private static final Expression getNextOrderExpression(StringTokenizer stringTokenizer, Path root) {
        if (stringTokenizer.countTokens() >= 2) {
            String alias = stringTokenizer.nextToken();
            Path aliasPath = root.get(alias);
            return getNextOrderExpression(stringTokenizer, aliasPath);
        }
        return root.get(stringTokenizer.nextToken());
    }

    @Override
    public long countAll() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        criteria.select(builder.count(criteria.from(persistentClass)));
        return getEntityManager().createQuery(criteria).getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T merge(T entity) {
        return getEntityManager().merge(entity);
    }

    @Override
    public T refresh(T entity) {
        getEntityManager().refresh(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        getEntityManager().persist(entity);
        return entity;
    }

    @Override
    public void flush() {
        getEntityManager().flush();
    }

    @Override
    public void lock(T entity, LockModeType lockModeType) {
        getEntityManager().lock(entity, lockModeType);
    }

    protected TypedQuery<T> applyPaging(CriteriaQuery<T> criteria, int offset, int limit) {
        return getEntityManager().createQuery(criteria).setFirstResult(offset).setMaxResults(limit);
    }

    public CriteriaQuery<T> createCriteriaFromSearchParams(Filter filter) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        return builder.createQuery(getDomainClass());
    }
}
