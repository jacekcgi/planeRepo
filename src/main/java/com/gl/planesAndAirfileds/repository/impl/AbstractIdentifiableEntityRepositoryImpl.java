package com.gl.planesAndAirfileds.repository.impl;

import com.gl.planesAndAirfileds.domain.AbstractIdentifiableEntity;
import com.gl.planesAndAirfileds.repository.CustomIdentifiableEntityRepository;
import org.springframework.stereotype.Component;

/**
 * Created by krzysztof.gonia on 3/7/2017.
 */
@Component
public abstract class AbstractIdentifiableEntityRepositoryImpl<T extends AbstractIdentifiableEntity>
        extends AbstractEntityRepositoryImpl<T> implements CustomIdentifiableEntityRepository<T> {

}
