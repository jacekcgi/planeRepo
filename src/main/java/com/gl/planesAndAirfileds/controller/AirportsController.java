package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.Airport;
import com.gl.planesAndAirfileds.domain.api.Mappings;
import com.gl.planesAndAirfileds.domain.dto.SearchResult;
import com.gl.planesAndAirfileds.domain.filter.AirportFilter;
import com.gl.planesAndAirfileds.domain.filter.SearchRequest;
import com.gl.planesAndAirfileds.service.AirportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = Mappings.FIND_AIRPORTS_ONZOOM_LVL, method = RequestMethod.GET)
    public List<Airport> getAirport(@PathVariable(value = "airport_zoomlevel") int zoomlvl) {
        return airportsService.getAirportOnZoomLvl(zoomlvl);
    }

    @RequestMapping(value = Mappings.FIND_AIRPORTS_BY, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public SearchResult<Airport> findAirportsBy(@RequestBody SearchRequest<AirportFilter> searchRequest) {
        return findBySearchParams(searchRequest, airportsService);
    }

}
