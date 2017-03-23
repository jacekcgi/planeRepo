package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.AbstractStatefulEntity;

/**
 * Created by krzysztof.gonia on 3/6/2017.
 */
public interface AbstractStatefulEntityRepository<T extends AbstractStatefulEntity>
        extends AbstractIdentifiableEntityRepository<T> {
}
