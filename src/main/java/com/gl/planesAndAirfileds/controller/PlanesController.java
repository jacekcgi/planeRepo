package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.domain.api.Mappings;
import com.gl.planesAndAirfileds.domain.dto.SearchResult;
import com.gl.planesAndAirfileds.domain.filter.PagingRequest;
import com.gl.planesAndAirfileds.domain.filter.PlaneFilter;
import com.gl.planesAndAirfileds.domain.filter.SearchRequest;
import com.gl.planesAndAirfileds.domain.simulator.GetFlightDetailsDto;
import com.gl.planesAndAirfileds.service.FlightDetailsService;
import com.gl.planesAndAirfileds.service.PlaneService;
import com.gl.planesAndAirfileds.validators.PlaneValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.util.List;

@RestController
public class PlanesController extends AbstractController {

    private PlaneService planeService;

    private PlaneValidator planeValidator;

    private FlightDetailsService flightDetailsService;

    @Autowired
    public PlanesController(PlaneService planeService, PlaneValidator planeValidator,
                            FlightDetailsService flightDetailsService) {
        this.planeService = planeService;
        this.planeValidator = planeValidator;
        this.flightDetailsService = flightDetailsService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        addValidator(binder, Plane.class, planeValidator);
    }

    @RequestMapping(value = Mappings.CREATE_PLANE, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public Plane save(@RequestBody @Validated(Default.class) Plane plane) {
        if (StringUtils.isEmpty(plane.getSid())) {
            return planeService.save(plane);
        }
        else {
            return planeService.update(plane);
        }
    }

    @RequestMapping(value = Mappings.CURRENT_FLIGHTS)
    @ResponseStatus(value = HttpStatus.OK)
    public List<GetFlightDetailsDto> findFlightsToSimulate() {
        return flightDetailsService.findLatestForSimulator();
    }

    @RequestMapping(value = Mappings.FIND_PLANES, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public SearchResult<Plane> findPlanes(@RequestBody SearchRequest<PlaneFilter> searchRequest) {
        PlaneFilter planeFilter = searchRequest.getFilter();
        PagingRequest pagingRequest = searchRequest.getPageRequest();
        return findBySearchParams(planeFilter, pagingRequest, planeService);
    }

    @RequestMapping(value = Mappings.GET_PLANE, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Plane getPlane(@PathVariable(value = "sid") String sid) {
        return planeService.getBySid(sid);
    }
}
