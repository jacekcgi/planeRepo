package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.FlightRoute;
import com.gl.planesAndAirfileds.domain.api.Mappings;
import com.gl.planesAndAirfileds.domain.dto.FlightRouteDto;
import com.gl.planesAndAirfileds.domain.dto.SearchResult;
import com.gl.planesAndAirfileds.domain.filter.FlightRouteFilter;
import com.gl.planesAndAirfileds.domain.filter.PagingRequest;
import com.gl.planesAndAirfileds.domain.filter.SearchRequest;
import com.gl.planesAndAirfileds.service.FlightRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by krzysztof.gonia on 3/27/2017.
 */
@RestController
public class FlightRouteController extends AbstractController {

    private FlightRouteService flightRouteService;

    @Autowired
    public FlightRouteController(FlightRouteService flightRouteService) {
        this.flightRouteService = flightRouteService;
    }

    @RequestMapping(value = Mappings.FIND_FLIGHT_ROUTES, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public SearchResult<FlightRoute> find(@RequestBody SearchRequest<FlightRouteFilter> searchRequest){

        FlightRouteFilter flightRouteFilter = searchRequest.getFilter();
        PagingRequest pagingRequest = searchRequest.getPageRequest();
        return findBySearchParams(flightRouteFilter, pagingRequest, flightRouteService);
    }

    @RequestMapping(value = Mappings.CREATE_FLIGHT_ROUTES, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public FlightRoute save(@RequestBody FlightRouteDto flightRouteDto) {
            return flightRouteService.save(flightRouteDto);
    }
}
