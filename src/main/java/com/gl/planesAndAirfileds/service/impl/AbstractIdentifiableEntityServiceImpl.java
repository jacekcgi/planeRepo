package com.gl.planesAndAirfileds.service.impl;

import com.gl.planesAndAirfileds.domain.AbstractIdentifiableEntity;
import com.gl.planesAndAirfileds.domain.exceptions.DataNotFoundException;
import com.gl.planesAndAirfileds.repository.AbstractIdentifiableEntityRepository;
import com.gl.planesAndAirfileds.service.AbstractIdentifiableEntityService;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
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

    @Override
    @Transactional
    public T update(T entity)
    {
        return update(entity, null);
    }

    @Transactional
    public T update(T entity, LockModeType lockModeType)
    {
        if (getUpdateIgnoreFields() != null)
        {
            T oldInstance = getBySid(entity.getSid());
            if (oldInstance == null)
            {
                throw new DataNotFoundException("entity with identifier " + entity.getSid() + " not found");
            }
            if (lockModeType != null)
            {
                lock(oldInstance, lockModeType);
            }
            BeanUtils.copyProperties(entity, oldInstance, getUpdateIgnoreFields());
            return getRepository().update(oldInstance);
        }
        else
        {
            return super.update(entity);
        }
    }

    protected String[] getUpdateIgnoreFields() {
        return new String[] {
                AbstractIdentifiableEntity.FIELD_ID,
                AbstractIdentifiableEntity.FIELD_SID
        };
    }
}
