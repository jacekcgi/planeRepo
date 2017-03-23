package com.gl.planesAndAirfileds.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {

    @RequestMapping(value =
            {
                    "/",
                    "/menu",
                    "/menu/**"
            }, method = RequestMethod.GET)
    public String mainMenu() {
        return "index";
    }

}
