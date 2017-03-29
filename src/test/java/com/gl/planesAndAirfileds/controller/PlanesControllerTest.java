package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.TestDomainObjectFactory;
import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.domain.api.Mappings;
import com.gl.planesAndAirfileds.domain.filter.*;
import com.gl.planesAndAirfileds.service.PlaneService;
import com.gl.planesAndAirfileds.validators.PlaneValidator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import org.springframework.web.bind.WebDataBinder;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PlanesController.class)
public class PlanesControllerTest {

    @MockBean
    WebDataBinder binder;

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

//    @Test
//    public void testSave() throws Exception {
//        Plane p1 = TestDomainObjectFactory.getPlane();
//        ObjectMapper mapper = new ObjectMapper();
//        Mockito.when(this.planeService.save(p1)).thenReturn(p1);
//        Mockito.doNothing().when(planeValidator).validate(p1,null);
//        this.mvc.perform(post(Mappings.CREATE_PLANE).content(mapper.writeValueAsString(p1)).contentType(contentType)).
//                andExpect(status().isOk());
//    }

    @Test
    public void testGetPlaneList() throws Exception {
        List<Plane> planes = new ArrayList<>();
        Plane p1 = TestDomainObjectFactory.getPlane();
        Plane p2 = TestDomainObjectFactory.getPlane();
        planes.add(p1);
        planes.add(p2);

        SearchRequest<PlaneFilter> searchRequest = new SearchRequest<>();
        List<OrderRequest> orderRequests = Arrays.asList(new OrderRequest(Plane.FIELD_NAME, true));
        searchRequest.setPageRequest(new PagingRequest(0, Integer.MAX_VALUE, new SortRequest(orderRequests)));

        Mockito.when(this.planeService.countBySearchParams(null))
                .thenReturn(2l);

        Mockito.when(this.planeService.findBySearchParams(null, searchRequest.getPageRequest().toPageRequest()))
                .thenReturn(planes);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        this.mvc.perform(post(Mappings.FIND_PLANES).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(gson.toJson(searchRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.entities", hasSize(2)))
                .andExpect(jsonPath("$.entities[0].name", is(p1.getName())))
                .andExpect(jsonPath("$.entities[0].registration", is(p1.getRegistration())))
                .andExpect(jsonPath("$.entities[0].description", is(p1.getDescription())))
                .andExpect(jsonPath("$.entities[0].sid", is(p1.getSid())))
                .andExpect(jsonPath("$.entities", hasSize(2)))
                .andExpect(jsonPath("$.entities[1].name", is(p2.getName())))
                .andExpect(jsonPath("$.entities[1].registration", is(p2.getRegistration())))
                .andExpect(jsonPath("$.entities[1].description", is(p2.getDescription())))
                .andExpect(jsonPath("$.entities[1].sid", is(p2.getSid())));
    }

}
