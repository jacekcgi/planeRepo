package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.AbstractEntity;

import java.io.Serializable;

/**
 * Created by marek.sroga on 2017-03-06.
 */
public interface AbstractEntityService<T extends AbstractEntity, ID extends Serializable> {

    <S extends T> S save(S entity);

    <S extends T> Iterable<S> save(Iterable<S> entities);

    T findOne(ID id);

    boolean exists(ID id);

    Iterable<T> findAll();

    Iterable<T> findAll(Iterable<ID> ids);

    long count();

    void delete(ID id);

    void delete(T entity);

    void delete(Iterable<? extends T> entities);

    void deleteAll();
}
