package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.Plane;

import java.util.List;

public interface PlaneRepository extends AbstractIdentifiableEntityRepository<Plane> {

    List<String> findPlanesSid();

    List<Plane> findAllContainsDescription(String description);

    long countByRegistration(String name, String ignoreSid);
}
