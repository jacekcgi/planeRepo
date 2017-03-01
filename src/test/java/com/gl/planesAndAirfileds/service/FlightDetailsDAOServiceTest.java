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

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightDetailsDAOService.class)
public class FlightDetailsDAOServiceTest {

    @MockBean
    private FlightDetailsRepository flightDetailsRepository;

    @Autowired
    private FlightDetailsDAOService flightDetailsDAOService;


    @Test
    public void shouldInvokeGetLatestFlightDetailForPlane() {

        List<FlightDetails> flightDetailsTestList = new ArrayList<>();
        flightDetailsTestList.add(new FlightDetails());

        when(flightDetailsRepository.getLatestFlightDetailForPlane(anyLong()))
                .thenReturn(flightDetailsTestList);

        flightDetailsDAOService.getLatestFlightDetailsForPlane(anyLong());

        verify(flightDetailsRepository).getLatestFlightDetailForPlane(anyLong());

    }

    @Test
    public void ifMethodGetNullAsParameterThenFlightDetailsForAllPlanesIsReturn() {

        flightDetailsDAOService.getLatestFlightDetailsForPlanes(null);

        verify(flightDetailsRepository).getLatestFlightDetailsForAllPlanes();

    }

  @Test
    public void ifMethodGetPlaneIdAsParameterThenFlightDetailFroThisPlaneIsReturn() {

        flightDetailsDAOService.getLatestFlightDetailsForPlanes(anyLong());

        verify(flightDetailsRepository).getLatestFlightDetailForPlane(anyLong());

    }

}
