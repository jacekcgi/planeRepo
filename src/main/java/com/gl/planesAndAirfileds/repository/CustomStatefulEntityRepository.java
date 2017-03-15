package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.AbstractStatefulEntity;

/**
 * Created by krzysztof.gonia on 3/7/2017.
 */
public interface CustomStatefulEntityRepository<T extends AbstractStatefulEntity>
        extends CustomIdentifiableEntityRepository<T> {
}
