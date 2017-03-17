package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.repository.FlightDetailsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightDetailsService.class)
public class FlightDetailsServiceTest {

    @MockBean
    private FlightDetailsRepository flightDetailsRepository;

    @MockBean
    private PlaneService planeService;

    @MockBean
    private FlightDetailsFactoryService flightDetailsFactoryService;

    @Autowired
    private FlightDetailsService flightDetailsService;

    @Test
    public void shouldInvokeGetLatestFlightDetailForPlane() {

        List<FlightDetails> flightDetailsTestList = new ArrayList<>();
        flightDetailsTestList.add(new FlightDetails());

        when(flightDetailsRepository.getLatestFlightDetails(anyString(),anyBoolean()))
                .thenReturn(flightDetailsTestList);

        flightDetailsService.getLatestFlightDetailsForPlanes(anyString(),anyBoolean());

        verify(flightDetailsRepository).getLatestFlightDetails(anyString(),anyBoolean());

    }

    @Test
    public void ifMethodGetNullAsParameterThenFlightDetailsForAllPlanesIsReturn() {
        flightDetailsService.getLatestFlightDetailsForPlanes(null,false);
        verify(flightDetailsRepository).getLatestFlightDetails(null,false);

    }

}
