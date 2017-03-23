package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.Plane;

import java.util.List;

/**
 * Created by marek.sroga on 2017-03-06.
 */
public interface PlaneService extends AbstractIdentifiableEntityService<Plane> {
    Plane getPlane(Long id);

    Plane save(Plane plane);

    List<String> findPlanesSid();

    boolean existRegistration(String name, String ignoreSid);
}
