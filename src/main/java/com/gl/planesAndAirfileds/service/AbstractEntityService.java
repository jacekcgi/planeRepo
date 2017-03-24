package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.AbstractEntity;
import com.gl.planesAndAirfileds.domain.filter.Filter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.LockModeType;
import java.util.Collection;
import java.util.List;

/**
 * Created by marek.sroga on 2017-03-06.
 */
public interface AbstractEntityService<T extends AbstractEntity> {

    T save(T entity);

    T update(T entity);

    void saveList(Collection<T> entities);

    List<T> findAll();

    List<T> findAll(String field, boolean ascending);

    void delete(T entity);

    void deleteList(Collection<T> entities);

    List<T> findBySearchParams(Filter filter, Pageable pageRequest);

    long countBySearchParams(Filter filter);

    long countAll();

    T merge(T entity);

    void refresh(T entity);

    void refresh(Collection<T> entities);

    void lock(T entity, LockModeType lockModeType);

    T getById(Long id);
}
