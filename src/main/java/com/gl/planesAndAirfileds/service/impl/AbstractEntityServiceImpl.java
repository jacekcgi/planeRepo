package com.gl.planesAndAirfileds.service.impl;

import com.gl.planesAndAirfileds.domain.AbstractEntity;
import com.gl.planesAndAirfileds.domain.filter.Filter;
import com.gl.planesAndAirfileds.repository.AbstractEntityRepository;
import com.gl.planesAndAirfileds.service.AbstractEntityService;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


/**
 * Created by marek.sroga on 2017-03-06.
 */
public abstract class AbstractEntityServiceImpl<T extends AbstractEntity, ID extends Serializable>
        implements AbstractEntityService<T, ID>
{
    protected abstract AbstractEntityRepository<T, ID> getRepository();

    @Override
    public <S extends T> S save(S entity) {
        return getRepository().save(entity);
    }

    @Override
    public <S extends T> Iterable<S> save(Iterable<S> entities) {
        return getRepository().save(entities);
    }

    @Override
    @Transactional(readOnly = true)
    public T findOne(ID id) {
        return getRepository().findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(ID id) {
        return getRepository().exists(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<T> findAll(Iterable<ID> ids) {
        return getRepository().findAll(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return getRepository().count();
    }

    @Override
    public void delete(ID id) {
        getRepository().delete(id);
    }

    @Override
    public void delete(T entity) {
        getRepository().delete(entity);
    }

    @Override
    public void delete(Iterable<? extends T> entities) {
        getRepository().delete(entities);
    }

    @Override
    public void deleteAll() {
        getRepository().deleteAll();
    }

    abstract  public List<T> findBySearchParams(Filter filter, PageRequest pageRequest);

    abstract public long countBySearchParams(Filter filter);

}
