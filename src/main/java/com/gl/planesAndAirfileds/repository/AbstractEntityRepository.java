package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.AbstractEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by krzysztof.gonia on 3/6/2017.
 */
@NoRepositoryBean
public interface AbstractEntityRepository<T extends AbstractEntity, S extends Serializable> extends CrudRepository<T, S> {
}
