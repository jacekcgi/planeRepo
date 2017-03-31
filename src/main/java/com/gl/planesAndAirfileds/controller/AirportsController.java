package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.Airport;
import com.gl.planesAndAirfileds.domain.api.Mappings;
import com.gl.planesAndAirfileds.domain.dto.SearchResult;
import com.gl.planesAndAirfileds.domain.filter.PlaneFilter;
import com.gl.planesAndAirfileds.domain.filter.SearchRequest;
import com.gl.planesAndAirfileds.service.AirportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AirportsController extends AbstractController {

    private AirportsService airportsService;

    @Autowired
    public AirportsController(AirportsService airportsService) {
        this.airportsService = airportsService;
    }

    @RequestMapping(value = Mappings.FIND_AIRPORTS, method = RequestMethod.GET)
    public Iterable<Airport> findAirports() {
        return airportsService.findAll();
    }

    @RequestMapping(value = Mappings.GET_AIRPORT, method = RequestMethod.GET)
    public Airport getAirport(@PathVariable(value = "airport_id") Long airportId) {
        return airportsService.getById(airportId);
    }

    @RequestMapping(value = Mappings.FIND_AIRPORTS_BY, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public SearchResult<Airport> findAirportsBy(@RequestBody SearchRequest<PlaneFilter> searchRequest) {
        return findBySearchParams(searchRequest, airportsService);
    }

}
