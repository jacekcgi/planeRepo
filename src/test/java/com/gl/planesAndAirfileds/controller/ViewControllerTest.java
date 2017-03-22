package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.service.PlaneService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
    PlaneService planeService;

    @Test
    public void dispalyMapTest() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TEXT_HTML_CHARSET_UTF_8))
                .andExpect(view().name("index"));

    }
}
