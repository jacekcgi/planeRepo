package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.AbstractIdentifiableEntity;

import java.util.List;

/**
 * Created by krzysztof.gonia on 3/6/2017.
 */
public interface AbstractIdentifiableEntityRepository<T extends AbstractIdentifiableEntity>
        extends AbstractEntityRepository<T> {

    T getBySid(String sid);

    List<T> findBySid(List<String> sids);
}
