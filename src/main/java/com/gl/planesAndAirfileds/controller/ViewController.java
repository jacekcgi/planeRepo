package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.service.PlaneDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ViewController {

    private PlaneDAOService planeDaoService;

    @Autowired
    public ViewController(PlaneDAOService planeDaoService) {
        this.planeDaoService = planeDaoService;
    }

    @RequestMapping("/")
    public String displayMap() {
        return "index";
    }


    @RequestMapping("/showPlane/{id}")
    public String displayOnePlaneMap(@PathVariable(value = "id") Long planeId, HttpSession session) {
        Plane plane = planeDaoService.getPlane(planeId);
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
