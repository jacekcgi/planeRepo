package com.gl.planesAndAirfileds.service.impl;

import com.gl.planesAndAirfileds.domain.AbstractEntity;
import com.gl.planesAndAirfileds.repository.AbstractEntityRepository;
import com.gl.planesAndAirfileds.service.AbstractEntityService;

import java.io.Serializable;


/**
 * Created by marek.sroga on 2017-03-06.
 */
public abstract class AbstractEntityServiceImpl<T extends AbstractEntity, S extends Serializable>
        implements AbstractEntityService<T, S>
{
    protected abstract AbstractEntityRepository<T, S> getRepository();
}
