package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.service.FlightDetailsService;
import com.gl.planesAndAirfileds.service.MaxDistanceCalculatorService;
import com.gl.planesAndAirfileds.service.PrimitiveConverterHelperService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(MaxDistanceController.class)
public class MaxDistanceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private FlightDetailsService flightDetailsService;

    @MockBean
    private MaxDistanceCalculatorService maxDistanceCalculatorService;

    @MockBean
    private PrimitiveConverterHelperService primitiveConverterHelperService;

    private static Double RETURN_VALUE_MAX_DISTANCE = 55d;

    @Test
    public void getMaxDistanceCalculationTest() throws Exception {

//        when(flightDetailsService.getLatestFlightDetailsForPlane(anyString(), anyBoolean()))
//                .thenReturn(new FlightDetails());
//
//        when(maxDistanceCalculatorService.calculateMaxDistance(anyDouble(), anyDouble()))
//                .thenReturn(RETURN_VALUE_MAX_DISTANCE);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/planeMaxDistance/c7621e93945d4c54bcd44040caa9aac9"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
    }
}
