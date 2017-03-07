package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.Plane;

import java.util.List;

/**
 * Created by krzysztof.gonia on 3/6/2017.
 */
public interface CustomPlaneRepository extends CustomAbstractEntityRepository<Plane> {

    List<Plane> findAllContainsDescription(String description);

    long countByRegistration(String name, String ignoreSid);
}
