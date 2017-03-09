package com.gl.planesAndAirfileds.repository.impl;

import com.gl.planesAndAirfileds.domain.NestedEntity;
import com.gl.planesAndAirfileds.repository.AbstractEntityRepository;
import org.springframework.stereotype.Repository;

/**
 * Additional interface for tests
 */

@Repository
public interface NestedEntityRepository extends AbstractEntityRepository<NestedEntity,Long>, CustomNestedEntityRepository {

}
