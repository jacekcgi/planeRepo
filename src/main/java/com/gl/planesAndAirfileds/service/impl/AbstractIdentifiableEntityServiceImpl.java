package com.gl.planesAndAirfileds.service.impl;

import com.gl.planesAndAirfileds.domain.AbstractIdentifiableEntity;
import com.gl.planesAndAirfileds.repository.AbstractIdentifiableEntityRepository;
import com.gl.planesAndAirfileds.service.AbstractIdentifiableEntityService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by marek.sroga on 2017-03-06.
 */
public abstract class AbstractIdentifiableEntityServiceImpl<T extends AbstractIdentifiableEntity>
        extends AbstractEntityServiceImpl<T> implements AbstractIdentifiableEntityService<T> {

    @Override
    protected abstract AbstractIdentifiableEntityRepository<T> getRepository();

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
