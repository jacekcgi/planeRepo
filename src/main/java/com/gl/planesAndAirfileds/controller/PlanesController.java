package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.domain.PlaneId;
import com.gl.planesAndAirfileds.domain.api.Mappings;
import com.gl.planesAndAirfileds.service.impl.PlaneServiceImpl;
import com.gl.planesAndAirfileds.validators.PlaneValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PlanesController extends AbstractController {

    private RestTemplate restTemplate;
    @Value("${simulator.plane.add.url}")
    private String simulatorPlaneAddUrl;
    private PlaneServiceImpl planeServiceImpl;

    private PlaneValidator planeValidator;

    @Autowired
    public PlanesController(PlaneServiceImpl planeServiceImpl, PlaneValidator planeValidator,RestTemplateBuilder builder) {
        this.planeServiceImpl = planeServiceImpl;
        this.planeValidator = planeValidator;
            this.restTemplate = builder.build();
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder)
    {
        addValidator(binder, Plane.class, planeValidator);
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
