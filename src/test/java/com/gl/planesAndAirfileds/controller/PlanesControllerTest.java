package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.TestDomainObjectFactory;
import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.service.PlaneService;
import com.gl.planesAndAirfileds.validators.PlaneValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
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
    private PlaneService planeService;

    @MockBean
    private PlaneValidator planeValidator;

    @MockBean
    private RestTemplateBuilder builder;

    @Autowired
    private MockMvc mvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Test
    public void testGetPlaneList() throws Exception {
        List<Plane> planes = new ArrayList<>();
        Plane p1 = TestDomainObjectFactory.getPlane();
        Plane p2 = TestDomainObjectFactory.getPlane();
        planes.add(p1);
        planes.add(p2);
        Mockito.when(this.planeService.getAllPlanes()).thenReturn(planes);
        //given(this.planeServiceImpl.getAllPlanes()).willReturn(planes);
        this.mvc.perform(get("/planeList"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(p1.getName())))
                .andExpect(jsonPath("$[0].registration", is(p1.getRegistration())))
                .andExpect(jsonPath("$[0].description", is(p1.getDescription())))
                .andExpect(jsonPath("$[0].sid", is(p1.getSid())))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].name", is(p2.getName())))
                .andExpect(jsonPath("$[1].registration", is(p2.getRegistration())))
                .andExpect(jsonPath("$[1].description", is(p2.getDescription())))
                .andExpect(jsonPath("$[1].sid", is(p2.getSid())));
    }

}
