package com.gl.planesAndAirfileds.service.impl;

import com.gl.planesAndAirfileds.domain.AbstractIdentifiableEntity;
import com.gl.planesAndAirfileds.domain.AbstractStatefulEntity;
import com.gl.planesAndAirfileds.repository.AbstractIdentifiableEntityRepository;
import com.gl.planesAndAirfileds.repository.AbstractStatefulEntityRepository;
import com.gl.planesAndAirfileds.service.AbstractStatefulEntityService;

import java.io.Serializable;

/**
 * Created by marek.sroga on 2017-03-06.
 */
public abstract class AbstractStatefulEntityServiceImpl<T extends AbstractStatefulEntity, S extends Serializable>
    extends AbstractIdentifiableEntityServiceImpl<T, S>  implements AbstractStatefulEntityService<T, S>
{
    @Override
    protected abstract AbstractStatefulEntityRepository<T, S> getRepository();
}
