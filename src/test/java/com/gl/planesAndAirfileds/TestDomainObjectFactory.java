package com.gl.planesAndAirfileds;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.domain.util.SidUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by krzysztof.gonia on 3/6/2017.
 */
public class TestDomainObjectFactory {

    private static final Random random = new Random();

    public static Plane getPlane() {
        Plane plane = new Plane();
        plane.setName(RandomStringUtils.random(10));
        plane.setRegistration(RandomStringUtils.random(10));
        plane.setDescription(RandomStringUtils.random(10));
        plane.setSid(SidUtils.generate());
        return plane;
    }

    public static List<Plane> getPlanes(int size) {
        List<Plane> planes = new ArrayList();
        for (int i = 0; i < size; i++) {
            planes.add(getPlane());
        }
        return planes;
    }
}
