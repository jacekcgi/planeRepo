package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.service.PlaneDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by marcin.majka on 15/2/2017.
 */
@RestController
public class PlanesController {

    private PlaneDAO planeDao;

    @Autowired
    public PlanesController(PlaneDAO planeDao) {
        this.planeDao = planeDao;
    }

    @RequestMapping( value = "/plane", method = RequestMethod.POST )
    public Plane save(@RequestBody Plane plane) {
        plane = planeDao.save(plane);
        return null;
    }
}
