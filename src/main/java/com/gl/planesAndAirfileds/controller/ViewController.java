package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {

    private PlaneService planeService;

    @Autowired
    public ViewController(PlaneService planeService) {
        this.planeService = planeService;
    }

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
