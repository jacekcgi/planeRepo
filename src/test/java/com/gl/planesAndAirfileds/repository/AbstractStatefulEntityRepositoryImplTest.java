package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.AbstractStatefulEntity;
import org.junit.Ignore;

@Ignore
public abstract class AbstractStatefulEntityRepositoryImplTest<T extends AbstractStatefulEntity>
        extends AbstractIdentifiableEntityRepositoryImplTest<T> {

    @Override
    protected abstract AbstractStatefulEntityRepository<T> getRepository();
}
