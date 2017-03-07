package com.gl.planesAndAirfileds.components;

import com.gl.planesAndAirfileds.domain.Airport;
import com.gl.planesAndAirfileds.service.AirportsDAOService;
import com.gl.planesAndAirfileds.service.AirportsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(AirPortsStorageWriter.class)
@ActiveProfiles("test")
public class AirPortsStorageWriterTest {

    @Autowired
    AirPortsStorageWriter airPortsStorageWriter;

    @MockBean
    ApplicationArguments applicationArguments;

    @MockBean
    AirportsDAOService airportsDAOService;

    @MockBean
    AirportsService airportsService;

    @Test
    public void ShouldSaveToStorageIfListOfAirportsHaveData() throws Exception {

        Airport airportOne= new Airport();
        Airport airportTwo = new Airport();

        List<Airport> airportsList = new ArrayList<>();
        airportsList.add(airportOne);
        airportsList.add(airportTwo);

        when(airportsService.getListOfAirports())
                .thenReturn(airportsList);

        airPortsStorageWriter.run(applicationArguments);

        verify(airportsDAOService).saveAirports(airportsList);

    }

    @Test
    public void ShouldNotSaveToStorageIfListOfAirportsIsEmpty() throws Exception {

        when(airportsService.getListOfAirports())
                .thenReturn(Collections.emptyList());

        airPortsStorageWriter.run(applicationArguments);

        verify(airportsDAOService,times(0)).saveAirports(anyListOf(Airport.class));


    }
}
