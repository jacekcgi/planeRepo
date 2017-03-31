package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.Plane;

/**
 * Created by marek.sroga on 2017-03-06.
 */
public interface PlaneService extends AbstractIdentifiableEntityService<Plane> {
    boolean existRegistration(String name, String ignoreSid);
}
