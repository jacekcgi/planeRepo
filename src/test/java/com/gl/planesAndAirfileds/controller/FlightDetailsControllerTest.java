package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.service.FlightDetailsDAOService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightDetailsController.class)
public class FlightDetailsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    FlightDetailsDAOService flightDetailsDAOService;

    @Test
    public void getCurrentTimeTest() throws Exception {

        mockMvc.perform(get("/getCurrentTime"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

    }

    @Test
    public void getCurrentPositionOfAllPlanesSuccessCaseThenStatusOkTest() throws Exception {

        mockMvc.perform(get("/planeLocation"))
                .andExpect(status().isOk())
                .andExpect(content().contentType((MediaType.APPLICATION_JSON_UTF8)));

    }

    @Test
    public void getCurrentPositionOfAllPlanesServiceUnavailableErrorReturnTest() throws Exception {

        when(flightDetailsDAOService.getLatestFlightDetailsForPlanes(anyLong()))
                .thenReturn(null);

        mockMvc.perform(get("/planeLocation"))
                .andExpect(status().isInternalServerError());

    }

    @Test
    public void getLatestFlightDetailsForPlaneSuccessCaseStatusOkTest() throws Exception {

        mockMvc.perform(get("/planeLocation/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType((MediaType.APPLICATION_JSON_UTF8)));

    }

    @Test
    public void getLatestFlightDetailsForPlaneServiceUnavailableCaseTest() throws Exception {

        when(flightDetailsDAOService.getLatestFlightDetailsForPlanes(anyLong()))
                .thenReturn(null);

        mockMvc.perform(get("/planeLocation/2"))
                .andExpect(status().isInternalServerError());

    }

    @Test
    public void latestFightDetailsForPlaneTest() throws Exception {

        when(flightDetailsDAOService.getLatestFlightDetailsForPlane(anyLong()))
                .thenReturn(new FlightDetails());

        mockMvc.perform(get("/flightDetails/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType((MediaType.APPLICATION_JSON_UTF8)));

    }

}
