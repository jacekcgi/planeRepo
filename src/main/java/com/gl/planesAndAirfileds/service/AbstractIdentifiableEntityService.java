package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.AbstractIdentifiableEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by marek.sroga on 2017-03-06.
 */
public interface AbstractIdentifiableEntityService<T extends AbstractIdentifiableEntity, S extends Serializable>
        extends AbstractEntityService<T, S> {
    T getBySid(String sid);

    List<T> findBySid(List<String> sids);
}
