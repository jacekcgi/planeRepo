package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.domain.PlaneSid;

import java.util.List;

/**
 * Created by marek.sroga on 2017-03-06.
 */
public interface PlaneService extends AbstractIdentifiableEntityService<Plane, Long>
{
    Plane getPlane(Long id);

    Plane save(Plane plane);

    List<PlaneSid> getAllPlanesSid();

    Iterable<Plane> getAllPlanes();

    boolean existRegistration(String name, String ignoreSid);
}
