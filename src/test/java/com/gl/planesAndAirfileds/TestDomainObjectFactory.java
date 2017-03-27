package com.gl.planesAndAirfileds;

import com.gl.planesAndAirfileds.domain.Airport;
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

    private static final String TEST_SID = "12345678";

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

    public static FlightDetails getFlightDetails(Plane p1) {
        FlightDetails flightDetails = new FlightDetails();
        flightDetails.setPlane(p1);
        flightDetails.setActualPosition(false);
        flightDetails.setAverageFuelConsumption(RANDOM.nextDouble());
        flightDetails.setCourse(RANDOM.nextDouble());
        flightDetails.setGpsLatitude(RANDOM.nextDouble());
        flightDetails.setGpsLongitude(RANDOM.nextDouble());
        flightDetails.setIncomingTime(new Date());
        flightDetails.setVelocity(RANDOM.nextFloat());
        flightDetails.setLanded(false);
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

    public static String getStaticTestSid() {
        return TEST_SID;
    }

    public static List<Airport> getAirportSpecificObjectWithTheStaticSid() {

        List<Airport> flightDetailsTestList = new ArrayList<>();

        Airport airportOne = new Airport();
        airportOne.setSid(TEST_SID);
        airportOne.setName("Goroka Airport");
        airportOne.setCity("Goroka");
        airportOne.setCountry("Papua New Guinea");
        airportOne.setIataCode("GKA");
        airportOne.setIcaoCode("AYGA");
        airportOne.setLatitude("-6.081689834590001");
        airportOne.setLongitude("145.391998291");
        airportOne.setAltitude("5282");
        airportOne.setTimezone("\\N");
        airportOne.setDaylightSavingTime("U");
        airportOne.setTzDatabaseTimeZone("Pacific/Port_Moresby");
        airportOne.setType("airport");
        airportOne.setSource("OurAirports");

        flightDetailsTestList.add(airportOne);

        return flightDetailsTestList;
    }

    public static List<Airport> getAirportListWithEmptyAirportObjectsInIt() {

        List<Airport> airportsList = new ArrayList<>();
        airportsList.add(new Airport());
        airportsList.add(new Airport());

        return airportsList;
    }
}
