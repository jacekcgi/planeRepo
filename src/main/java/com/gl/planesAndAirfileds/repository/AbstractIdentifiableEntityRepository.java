package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.AbstractIdentifiableEntity;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by krzysztof.gonia on 3/6/2017.
 */
@NoRepositoryBean
interface AbstractIdentifiableEntityRepository<T extends AbstractIdentifiableEntity, S extends Serializable> extends AbstractEntityRepository<T, S> {

    T getBySid(String sid);

    List<T> findBySid(List<String> sids);
}
