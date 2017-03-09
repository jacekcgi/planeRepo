package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.Airport;
import com.gl.planesAndAirfileds.domain.api.Mappings;
import com.gl.planesAndAirfileds.service.AirportsDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AirportsController {

    private AirportsDAOService airportsDAOService;

    @Autowired
    public AirportsController(AirportsDAOService airportsDAOService) {
        this.airportsDAOService = airportsDAOService;
    }

    @RequestMapping(value = Mappings.FIND_AIRPORTS, method = RequestMethod.GET)
    public Iterable<Airport> findAirports()
    {
        return airportsDAOService.findAirports();
    }

    @RequestMapping(value = Mappings.GET_AIRPORT, method = RequestMethod.GET)
    public Airport getAirport(@PathVariable(value = "airport_id") Long airportId )
    {
        return airportsDAOService.getAirport(airportId);
    }

}
