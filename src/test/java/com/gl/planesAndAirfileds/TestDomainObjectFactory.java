package com.gl.planesAndAirfileds;

import com.gl.planesAndAirfileds.domain.*;
import com.gl.planesAndAirfileds.domain.util.SidUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
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

    public static FlightDetails getFlightDetails(FlightRoute flightRoute) {
        FlightDetails flightDetails = new FlightDetails();
        flightDetails.setAverageFuelConsumption(RANDOM.nextDouble());
        flightDetails.setGpsLatitude(RANDOM.nextDouble());
        flightDetails.setGpsLongitude(RANDOM.nextDouble());
        flightDetails.setVelocity(RANDOM.nextFloat());
        flightDetails.setFlightRoute(flightRoute);
        flightDetails.setCreatedDate(LocalDateTime.now());
        flightDetails.setDistanceTraveled(RANDOM.nextDouble());
        flightDetails.setActualPosition(false);
        flightDetails.setRemainingFuel(RANDOM.nextDouble());
        flightDetails.setAverageFuelConsumption(RANDOM.nextDouble());
        return flightDetails;
    }

    public static NestedEntity getNestedEntity(NestedEntity childNestedEntity) {
        NestedEntity nestedEntity = new NestedEntity();
        nestedEntity.setTestString(RandomStringUtils.random(10));
        nestedEntity.setNestedEntity(childNestedEntity);
        return nestedEntity;
    }

    public static NestedEntity getNestedEntity() {
        return getNestedEntity(null);
    }

    public static List<FlightRoute> findPlaneRoutes(Plane plane, Airport sourceAirport, Airport destinationAirport,
                                                    int count) {
        List<FlightRoute> flightRoutes = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            flightRoutes.add(getFlightRoute(plane, sourceAirport, destinationAirport));
        }
        return flightRoutes;

    }

    public static FlightRoute getFlightRoute(Plane plane, Airport sourceAirport, Airport destinationAirport) {
        FlightRoute flightRoute = new FlightRoute();
        flightRoute.setPlane(plane);
        flightRoute.setSource(sourceAirport);
        flightRoute.setDestination(destinationAirport);
        flightRoute.setIncomingDate(LocalDateTime.now());
        flightRoute.setStartDate(LocalDateTime.now());
        flightRoute.setFlightPhase(FlightPhase.READY);
        flightRoute.setFlightDistance(0);
        return flightRoute;
    }

    public static Airport getAirport() {
        Airport airport = new Airport();
        airport.setAltitude(RANDOM.nextDouble());
        airport.setLongitude(RANDOM.nextDouble());
        airport.setAltitude(RANDOM.nextDouble());
        airport.setName(SidUtils.generate());
        return airport;
    }

    public static List<FlightDetails> findFlightDetails(FlightRoute flightRoute, int count) {
        List<FlightDetails> flightDetails = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            flightDetails.add(getFlightDetails(flightRoute));
        }
        return flightDetails;
    }

    public static User getUser() {
        User user = new User();
        user.setSid(SidUtils.generate());
        user.setLogin(RandomStringUtils.randomAlphabetic(10));
        user.setName(RandomStringUtils.randomAlphabetic(10));
        user.setSurname(RandomStringUtils.randomAlphabetic(10));
        return user;
    }

    public static List<User> findUsers(int count) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            users.add(getUser());
        }
        return users;
    }

    public static Password getPassword(User user) {
        return new Password(RandomStringUtils.randomAlphabetic(10), user);
    }

    public static List<Password> findPasswords(List<User> users) {
        List<Password> passwords = new ArrayList<>();
        for (User user: users) {
            passwords.add(getPassword(user));
        }
        return passwords;
    }

}
