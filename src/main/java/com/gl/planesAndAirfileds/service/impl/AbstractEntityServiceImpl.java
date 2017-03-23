package com.gl.planesAndAirfileds.service.impl;

import com.gl.planesAndAirfileds.domain.AbstractEntity;
import com.gl.planesAndAirfileds.domain.filter.Filter;
import com.gl.planesAndAirfileds.repository.AbstractEntityRepository;
import com.gl.planesAndAirfileds.service.AbstractEntityService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.Collection;
import java.util.List;

/**
 * Created by marek.sroga on 2017-03-06.
 */
public abstract class AbstractEntityServiceImpl<T extends AbstractEntity>
        implements AbstractEntityService<T> {

    protected abstract AbstractEntityRepository<T> getRepository();

    @Override
    @Transactional
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    @Transactional
    public T update(T entity) {
        return getRepository().update(entity);
    }

    @Override
    @Transactional
    public void saveList(Collection<T> entities) {
        getRepository().saveList(entities);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return getRepository().findAll(null, true);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll(String field, boolean ascending) {
        return getRepository().findAll(field, ascending);
    }

    @Override
    @Transactional(readOnly = true)
    public long countAll() {
        return getRepository().countAll();
    }

    @Override
    @Transactional
    public void delete(T entity) {
        getRepository().delete(entity);
    }

    @Override
    @Transactional
    public void deleteList(Collection<T> entities) {
        if (CollectionUtils.isNotEmpty(entities)) {
            for (T obj : entities) {
                delete(obj);
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public T merge(T entity) {
        return getRepository().merge(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public void refresh(T entity) {
        getRepository().refresh(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public void refresh(Collection<T> entities) {
        if (CollectionUtils.isNotEmpty(entities)) {
            for (T entity : entities) {
                getRepository().refresh(entity);
            }
        }
    }

    @Override
    public void lock(T entity, LockModeType lockModeType) {
        getRepository().lock(entity, lockModeType);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findBySearchParams(Filter filter, Pageable pageRequest) {
        return getRepository().findBySearchParams(filter, pageRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public long countBySearchParams(Filter filter) {
        return getRepository().countBySearchParams(filter);
    }

    @Override
    @Transactional(readOnly = true)
    public T getById(Long id) {
        return getRepository().getById(id);
    }
}
