package com.gl.planesAndAirfileds.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by marcin.majka on 15/2/2017.
 */

@Controller
public class ViewController {

    @RequestMapping("/")
    public String displayMap() {
        return "index";
    }

    @RequestMapping("/plane")
    public String displayPlane() {
        return "plane";
    }
}
