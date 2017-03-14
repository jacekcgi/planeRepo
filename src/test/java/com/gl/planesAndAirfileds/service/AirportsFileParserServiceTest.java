package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.Airport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(AirportsFileParserService.class)
@ActiveProfiles("test")
public class AirportsFileParserServiceTest {

    @Autowired
    private AirportsFileParserService airportsFileParserService;

    @Test
    public void checkIfFileIsParseCorrectly() {

        List<Airport> expectedAirportList = getTesData();
        List<Airport> actualAirportList = airportsFileParserService.getListOfAirports();

        assertThat(actualAirportList, is(expectedAirportList));

    }

    private List<Airport> getTesData() {

        List<Airport> flightDetailsTestList = new ArrayList<>();

        Airport airportOne = new Airport();
        airportOne.setId(1l);
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

}
