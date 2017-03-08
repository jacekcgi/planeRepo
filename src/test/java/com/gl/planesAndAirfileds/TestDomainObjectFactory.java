package com.gl.planesAndAirfileds;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.domain.NestedEntity;
import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.domain.util.SidUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by krzysztof.gonia on 3/6/2017.
 */
public class TestDomainObjectFactory {

    private static final Random RANDOM = new Random();

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

    public static NestedEntity getNestedEntity(Plane plane) {
        NestedEntity nestedEntity = new NestedEntity();
        nestedEntity.setTestString(RandomStringUtils.random(10));
        nestedEntity.setPlane(plane);
        return nestedEntity;
    }

    public static FlightDetails getFlightDetails(Plane p1) {
        FlightDetails flightDetails = new FlightDetails();
        flightDetails.setPlane(p1);
        flightDetails.setActualPosition(false);
        flightDetails.setAverageFuelConsumption(RANDOM.nextInt());
        flightDetails.setCourse(RANDOM.nextDouble());
        flightDetails.setGpsLatitude(RANDOM.nextDouble());
        flightDetails.setGpsLongitude(RANDOM.nextDouble());
        flightDetails.setIncomingTime(new Date());
        flightDetails.setVelocity(RANDOM.nextFloat());
        return flightDetails;
    }
}
