package com.gl.planesAndAirfileds.service.impl;

import com.gl.planesAndAirfileds.domain.AbstractIdentifiableEntity;
import com.gl.planesAndAirfileds.repository.AbstractIdentifiableEntityRepository;
import com.gl.planesAndAirfileds.service.AbstractIdentifiableEntityService;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by marek.sroga on 2017-03-06.
 */
public abstract class AbstractIdentifiableEntityServiceImpl<T extends AbstractIdentifiableEntity, S extends Serializable>
        extends AbstractEntityServiceImpl<T, S> implements AbstractIdentifiableEntityService<T, S>
{
    @Override
    protected abstract AbstractIdentifiableEntityRepository<T, S> getRepository();

    @Override
    @Transactional(readOnly = true)
    public T getBySid(String sid) {
        return getRepository().getBySid(sid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findBySid(List<String> sids) {
        return getRepository().findBySid(sids);
    }
}
