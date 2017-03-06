package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.AbstractStatefulEntity;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by krzysztof.gonia on 3/6/2017.
 */
@NoRepositoryBean
public interface AbstractStatefulEntityRepository<T extends AbstractStatefulEntity, S extends Serializable> extends AbstractIdentifiableEntityRepository<T, S> {
}
