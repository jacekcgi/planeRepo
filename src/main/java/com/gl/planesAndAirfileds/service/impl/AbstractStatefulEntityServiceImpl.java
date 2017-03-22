package com.gl.planesAndAirfileds.service.impl;

import com.gl.planesAndAirfileds.domain.AbstractStatefulEntity;
import com.gl.planesAndAirfileds.repository.AbstractStatefulEntityRepository;
import com.gl.planesAndAirfileds.service.AbstractStatefulEntityService;

/**
 * Created by marek.sroga on 2017-03-06.
 */
public abstract class AbstractStatefulEntityServiceImpl<T extends AbstractStatefulEntity>
        extends AbstractIdentifiableEntityServiceImpl<T> implements AbstractStatefulEntityService<T> {
    @Override
    protected abstract AbstractStatefulEntityRepository<T> getRepository();
}
