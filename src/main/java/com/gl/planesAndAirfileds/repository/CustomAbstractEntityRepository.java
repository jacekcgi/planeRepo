package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.AbstractEntity;
import com.gl.planesAndAirfileds.domain.filter.Filter;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by krzysztof.gonia on 3/7/2017.
 */
public interface CustomAbstractEntityRepository<T extends AbstractEntity> {

    List<T> findBySearchParams(Filter filter, PageRequest pageRequest);

    long countBySearchParams(Filter filter);
}
