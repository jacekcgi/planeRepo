package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.domain.PlaneId;
import com.gl.planesAndAirfileds.domain.api.Mappings;
import com.gl.planesAndAirfileds.service.impl.PlaneServiceImpl;
import com.gl.planesAndAirfileds.validators.PlaneTestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PlanesController extends AbstractController {

    private PlaneServiceImpl planeServiceImpl;

    private PlaneTestValidator planeTestValidator;

    @Autowired
    public PlanesController(PlaneServiceImpl planeServiceImpl, PlaneTestValidator planeTestValidator) {
        this.planeServiceImpl = planeServiceImpl;
        this.planeTestValidator = planeTestValidator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder)
    {
        addValidator(binder, Plane.class, planeTestValidator);
    }

    @RequestMapping( value = Mappings.CREATE_PLANE, method = RequestMethod.POST )
    @ResponseStatus(value = HttpStatus.OK)
    public Plane save(@RequestBody @Valid Plane plane) {
        return planeServiceImpl.save(plane);
    }

    @RequestMapping(value = Mappings.FIND_PLANES)
    @ResponseStatus(value = HttpStatus.OK)
    public Iterable<Plane> getPlaneList() {
        return planeServiceImpl.getAllPlanes();
    }

    @RequestMapping(value = Mappings.FIND_PLANE_IDS)
    @ResponseStatus(value = HttpStatus.OK)
    public List<PlaneId> getPlanesId() {
        return planeServiceImpl.getAllPlanesId();
    }
}
