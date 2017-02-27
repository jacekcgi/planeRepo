package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.domain.PlaneId;
import com.gl.planesAndAirfileds.service.PlaneDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<Iterable<Plane>> getPlaneList() {
        Iterable<Plane> planes = planeDaoService.getAllPlanes();
        if(planes == null) {
            return  new ResponseEntity<Iterable<Plane>>(HttpStatus.BAD_REQUEST);
        } else {
            return  new ResponseEntity<Iterable<Plane>>(planes,HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/planeIdList")
    public List<PlaneId> getPlanesId() {
        return planeDaoService.getAllPlanesId();
    }
}
