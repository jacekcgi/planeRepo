package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.service.PlaneDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanesController {

    private PlaneDAOService planeDaoService;

    @Autowired
    public PlanesController(PlaneDAOService planeDaoService) {
        this.planeDaoService = planeDaoService;
    }

    @RequestMapping( value = "/plane", method = RequestMethod.POST )
    public ResponseEntity<Plane> save(@RequestBody Plane plane) {
        Plane savedPlane = planeDaoService.save(plane);

        if(savedPlane == null) {
            return  new ResponseEntity<Plane>(plane,HttpStatus.BAD_REQUEST);
        } else {
            return  new ResponseEntity<Plane>(savedPlane,HttpStatus.OK);
        }
    }
    @RequestMapping(value = "/planeList")
    public Iterable<Plane> getPlaneList() {
        return planeDaoService.getAllPlanes();
    }

}
