package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.Airport;
import com.gl.planesAndAirfileds.service.AirportsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@WebMvcTest(AirportsController.class)
public class AirportsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AirportsService airportsService;

    @Test
    public void findAirportsTest() throws Exception {

        when(airportsService.findAll())
                .thenReturn(new ArrayList<Airport>());

        mockMvc.perform(MockMvcRequestBuilders.get("/find/airports"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();

    }

    @Test
    public void getOneAirportTest() throws Exception {

        when(airportsService.getById(Matchers.anyLong()))
                .thenReturn(new Airport());

        mockMvc.perform(get("/getAirport/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

    }

}
