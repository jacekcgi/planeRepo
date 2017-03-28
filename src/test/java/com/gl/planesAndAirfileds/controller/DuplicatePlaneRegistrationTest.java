package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.service.PlaneService;
import com.gl.planesAndAirfileds.validators.PlaneValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@WebMvcTest(PlanesController.class)
public class DuplicatePlaneRegistrationTest {

    @MockBean
    private PlaneService planeService;

    @MockBean
    private PlaneValidator planeValidator;

    @MockBean
    private RestTemplateBuilder builder;

    private MockMvc mockMvc;

    private PlanesController controller;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Test
    public void testDuplicateRegPlane() throws Exception {
        // TODO: REWRITE
//        controller = new PlanesController(planeService, new PlaneValidator(planeService), builder);
//        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
//                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
//        Plane p1 = TestDomainObjectFactory.getPlane();
//        Plane p2 = TestDomainObjectFactory.getPlane();
//        ObjectMapper map = new ObjectMapper();
//        String planeDetailsJson = map.writeValueAsString(p1);
//
//        this.mockMvc.perform(post("/plane").contentType(contentType)
//                .content(planeDetailsJson)).andExpect(status().isOk());
//
//        when(planeService.existRegistration(anyString(), anyString()))
//                .thenReturn(true);  //Simulating duplicate registration for a plane
//        planeDetailsJson = map.writeValueAsString(p2);
//        this.mockMvc.perform(post("/plane").contentType(contentType)
//                .content(planeDetailsJson)).andExpect(status().isBadRequest());
    }

}