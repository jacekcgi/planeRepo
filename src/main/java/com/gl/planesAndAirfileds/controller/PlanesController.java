package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.service.PlaneDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Iterable<Plane> getPlaneList() {
        return planeDaoService.getAllPlanes();
    }

    /**
     * Get from database position of all planes
     * @return
     */
    @RequestMapping( value = "/planeLocation", method = RequestMethod.GET )
    public ResponseEntity<List<FlightDetails>> getCurrentPositionOfAllPlanes() {
        List<FlightDetails> currentPositionOfAllPlanes = planeDaoService.getCurrentPositionOfPlanes(null);
        if(currentPositionOfAllPlanes == null) {
            return  new ResponseEntity<List<FlightDetails>>(HttpStatus.BAD_REQUEST);
        } else {
            return  new ResponseEntity<List<FlightDetails>>(currentPositionOfAllPlanes,HttpStatus.OK);
        }
    }

    /**
     * Get from database position of selected plane
     * @return list of FligtDetails
     */
    @RequestMapping( value = "/planeLocation/{id}", method = RequestMethod.GET )
    public ResponseEntity<List<FlightDetails>> getCurrentPositionOfPlane(@PathVariable(value="id") Long planeId) {
        List<FlightDetails> currentPositionOfAllPlanes = planeDaoService.getCurrentPositionOfPlanes(planeId);
        if(currentPositionOfAllPlanes == null) {
            return  new ResponseEntity<List<FlightDetails>>(HttpStatus.BAD_REQUEST);
        } else {
            return  new ResponseEntity<List<FlightDetails>>(currentPositionOfAllPlanes,HttpStatus.OK);
        }
    }


}
