package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.AbstractStatefulEntity;

import java.io.Serializable;

/**
 * Created by marek.sroga on 2017-03-06.
 */
public interface AbstractStatefulEntityService<T extends AbstractStatefulEntity, S extends Serializable>
        extends AbstractIdentifiableEntityService<T, S>
{
}
