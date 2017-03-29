package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.Airport;
import com.gl.planesAndAirfileds.domain.util.SidUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

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

        Airport airportExpected = getExpectedAirport();

        List<Airport> actualAirportList = airportsFileParserService.getListOfAirports();
        Airport airportActual = actualAirportList.get(0);

        checkIfFieldValueAreTheSame(airportActual,airportExpected);

    }

    private void checkIfFieldValueAreTheSame(Airport airportActual, Airport airportExpected) {

        assertThat(airportActual.getName(), is(airportExpected.getName()));
        assertThat(airportActual.getCity(), is(airportExpected.getCity()));
        assertThat(airportActual.getCountry(), is(airportExpected.getCountry()));
        assertThat(airportActual.getIataCode(), is(airportExpected.getIataCode()));
        assertThat(airportActual.getIcaoCode(), is(airportExpected.getIcaoCode()));
        assertThat(airportActual.getLatitude(), is(airportExpected.getLatitude()));
        assertThat(airportActual.getLongitude(), is(airportExpected.getLongitude()));
        assertThat(airportActual.getAltitude(), is(airportExpected.getAltitude()));
        assertThat(airportActual.getTimezone(), is(airportExpected.getTimezone()));
        assertThat(airportActual.getDaylightSavingTime(), is(airportExpected.getDaylightSavingTime()));
        assertThat(airportActual.getTzDatabaseTimeZone(), is(airportExpected.getTzDatabaseTimeZone()));
        assertThat(airportActual.getType(), is(airportExpected.getType()));
        assertThat(airportActual.getSource(), is(airportExpected.getSource()));

    }

    private Airport getExpectedAirport() {

        Airport airportExpected = new Airport();
        airportExpected.setSid(SidUtils.generate());
        airportExpected.setName("Goroka Airport");
        airportExpected.setCity("Goroka");
        airportExpected.setCountry("Papua New Guinea");
        airportExpected.setIataCode("GKA");
        airportExpected.setIcaoCode("AYGA");
        airportExpected.setLatitude("-6.081689834590001");
        airportExpected.setLongitude("145.391998291");
        airportExpected.setAltitude("5282");
        airportExpected.setTimezone("\\N");
        airportExpected.setDaylightSavingTime("U");
        airportExpected.setTzDatabaseTimeZone("Pacific/Port_Moresby");
        airportExpected.setType("airport");
        airportExpected.setSource("OurAirports");

        return airportExpected;
    }


}
