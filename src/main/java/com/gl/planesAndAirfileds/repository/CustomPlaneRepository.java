package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.Plane;

import java.util.List;

/**
 * Created by krzysztof.gonia on 3/6/2017.
 */
public interface CustomPlaneRepository {

    List<Plane> findAllContainsDescription(String description);
}
