package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.Airport;
import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.domain.api.Mappings;
import com.gl.planesAndAirfileds.domain.dto.SearchResult;
import com.gl.planesAndAirfileds.domain.filter.AirportFilter;
import com.gl.planesAndAirfileds.domain.filter.PagingRequest;
import com.gl.planesAndAirfileds.domain.filter.PlaneFilter;
import com.gl.planesAndAirfileds.domain.filter.SearchRequest;
import com.gl.planesAndAirfileds.service.AirportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AirportsController {

    private AirportsService airportsService;

    @Autowired
    public AirportsController(AirportsService airportsService) {
        this.airportsService = airportsService;
    }

    @RequestMapping(value = Mappings.FIND_AIRPORTS, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public SearchResult<Airport> findAirports(@RequestBody SearchRequest<AirportFilter> searchRequest) {

        AirportFilter airportFilter = searchRequest.getFilter();

        long count = airportsService.countBySearchParams(airportFilter);
        List<Airport> airports = new ArrayList<>();
        if (count > 0) {
            PagingRequest pageRequest = searchRequest.getPageRequest();
            int page = pageRequest.getPage();
            int size = pageRequest.getSize();
            int pages = (int) Math.ceil((double) count / (double) size);
            if (page >= pages) {
                pageRequest.setPage(pages - 1);
            }

            airports = airportsService.findBySearchParams(searchRequest.getFilter(), pageRequest.toPageRequest());
        }
        return new SearchResult<>(airports, count, searchRequest.getPageRequest());
    }

    @RequestMapping(value = Mappings.GET_AIRPORT, method = RequestMethod.GET)
    public Airport getAirport(@PathVariable(value = "airport_id") Long airportId) {
        return airportsService.getById(airportId);
    }

}
