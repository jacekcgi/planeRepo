package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.service.PlaneDAOService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PlanesController.class)
public class PlanesControllerTest {

    @MockBean
    private PlaneDAOService planeDaoService;

    @Autowired
    private MockMvc mvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Test
    public void testGetPlaneList() throws Exception {
        List<Plane> planes = new ArrayList<>();
        Plane p1 = new Plane(1l,"plane 1","ATZ341","plane description 1");
        Plane p2 = new Plane(2l,"plane 2","ATZ342","plane description 2");
        planes.add(p1);
        planes.add(p2);
        Mockito.when(this.planeDaoService.getAllPlanes()).thenReturn(planes);
        //given(this.planeDaoService.getAllPlanes()).willReturn(planes);
        this.mvc.perform(get("/planeList"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("plane 1")))
                .andExpect(jsonPath("$[0].registration", is("ATZ341")))
                .andExpect(jsonPath("$[0].description", is("plane description 1")))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("plane 2")))
                .andExpect(jsonPath("$[1].registration", is("ATZ342")))
                .andExpect(jsonPath("$[1].description", is("plane description 2")));
    }

    @Test
    public void testGetPlaneListNull() throws Exception {
        Mockito.when(this.planeDaoService.getAllPlanes()).thenReturn(null);
        //given(this.planeDaoService.getAllPlanes()).willReturn(null);
        this.mvc.perform(get("/planeList"))
                .andExpect(status().isInternalServerError());
    }

}
