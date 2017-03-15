package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.AbstractIdentifiableEntity;

/**
 * Created by krzysztof.gonia on 3/7/2017.
 */
public interface CustomIdentifiableEntityRepository<T extends AbstractIdentifiableEntity>
        extends CustomAbstractEntityRepository<T> {
}
