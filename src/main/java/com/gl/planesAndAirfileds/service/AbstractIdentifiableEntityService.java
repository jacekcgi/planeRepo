package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.AbstractIdentifiableEntity;

import java.util.List;

/**
 * Created by marek.sroga on 2017-03-06.
 */
public interface AbstractIdentifiableEntityService<T extends AbstractIdentifiableEntity>
        extends AbstractEntityService<T> {

    T getBySid(String sid);

    List<T> findBySid(List<String> sids);
}
