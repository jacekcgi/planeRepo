package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ViewController {

    private PlaneService planeService;

    @Autowired
    public ViewController(PlaneService planeService) {
        this.planeService = planeService;
    }

    @RequestMapping("/")
    public String displayMap() {
        return "index";
    }

    @RequestMapping("/showPlane/{sid}")
    public String displayOnePlaneMap(@PathVariable(value = "sid") String planeSid, HttpSession session) {
        Plane plane = planeService.getBySid(planeSid);
        if (plane != null) {
            session.setAttribute("plane", plane);
        }
        return "redirect:/";
    }

    @RequestMapping("/showAllPlanes")
    public String showAllPlanes(HttpSession session) {
        session.removeAttribute("plane");
        return "redirect:/";
    }

    @RequestMapping("/plane")
    public String displayPlane() {
        return "plane";
    }

    @RequestMapping("/flightDetailsView")
    public String displayFlightDetails() {
        return "flightDetails";
    }

}
