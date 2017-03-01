package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.service.PlaneDAOService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ViewController.class)
public class ViewControllerTest {

    public static final String PLANE_NAME = "plane";
    public static final String TEXT_HTML_CHARSET_UTF_8 = "text/html;charset=UTF-8";
    public static final String REDIRECT_TO_HOME_VIEW = "redirect:/";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PlaneDAOService planeDaoService;

    @Test
    public void dispalyMapTest() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TEXT_HTML_CHARSET_UTF_8))
                .andExpect(view().name("index"));

    }

    @Test
    public void dispalyPlaneTest() throws Exception {

        mockMvc.perform(get("/plane"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TEXT_HTML_CHARSET_UTF_8))
                .andExpect(view().name(PLANE_NAME));

    }

    @Test
    public void dispalyFlightDetailsTest() throws Exception {

        mockMvc.perform(get("/flightDetailsView"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TEXT_HTML_CHARSET_UTF_8))
                .andExpect(view().name("flightDetails"));

    }

    @Test
    public void showAllPlanesAndSessionAttributePlaneIsRemovedTest() throws Exception {

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute(PLANE_NAME, new Plane());

        mockMvc.perform(get("/showAllPlanes").session(mockHttpSession))
                .andExpect(status().isFound())
                .andExpect(view().name(REDIRECT_TO_HOME_VIEW));

        Assert.assertNull(mockHttpSession.getAttribute(PLANE_NAME));
    }

    @Test
    public void displayOnePlaneMapTest() throws Exception {

        when(planeDaoService.getPlane(anyLong()))
                .thenReturn(new Plane());

        MockHttpSession mockHttpSession = new MockHttpSession();

        mockMvc.perform(get("/showPlane/1").session(mockHttpSession))
                .andExpect(status().isFound())
                .andExpect(view().name(REDIRECT_TO_HOME_VIEW));

        Assert.assertNotNull(mockHttpSession.getAttribute(PLANE_NAME));
    }
}
