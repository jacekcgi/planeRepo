package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.AbstractEntity;
import com.gl.planesAndAirfileds.domain.filter.Filter;
import org.springframework.data.domain.Pageable;

import javax.persistence.LockModeType;
import java.util.Collection;
import java.util.List;

/**
 * Created by krzysztof.gonia on 3/6/2017.
 */
public interface AbstractEntityRepository<T extends AbstractEntity> {

    List<T> findBySearchParams(Filter filter, Pageable pageRequest);

    long countBySearchParams(Filter filter);

    T save(T entity);

    void saveList(Collection<T> entities);

    void delete(T entity);

    void deleteList(Collection<T> entities);

    T getById(Long id);

    List<T> findAll(String orderBy, boolean ascending);

    long countAll();

    T merge(T entity);

    T refresh(T entity);

    T update(T entity);

    /**
     * The flush mode determines the points at which the session is flushed.</br>
     * <i>Flushing</i> is the process of synchronizing the underlying persistent.</br>
     * store with persistable state held in memory.</br>
     */
    void flush();

    void lock(T entity, LockModeType lockModeType);
}
