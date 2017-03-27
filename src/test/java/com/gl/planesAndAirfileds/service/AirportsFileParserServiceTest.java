package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.TestDomainObjectFactory;
import com.gl.planesAndAirfileds.domain.Airport;
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

        List<Airport> expectedAirportList = TestDomainObjectFactory.getAirportSpecificObjectWithTheStaticSid();

        List<Airport> actualAirportList = airportsFileParserService.getListOfAirports();
        actualAirportList.get(0).setSid(TestDomainObjectFactory.getStaticTestSid());

        assertThat(actualAirportList, is(expectedAirportList));

    }

}
