package com.gl.planesAndAirfileds.components;

import com.gl.planesAndAirfileds.domain.Airport;
import com.gl.planesAndAirfileds.service.AirportsDAOService;
import com.gl.planesAndAirfileds.service.AirportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AirPortsStorageWriter implements ApplicationRunner {

    private AirportsDAOService airportsDAOService;

    private AirportsService airportsService;

    public AirportsDAOService getAirportsDAOService() {
        return airportsDAOService;
    }

    @Autowired
    public void setAirportsDAOService(AirportsDAOService airportsDAOService) {
        this.airportsDAOService = airportsDAOService;
    }

    public AirportsService getAirportsService() {
        return airportsService;
    }

    @Autowired
    public void setAirportsService(AirportsService airportsService) {
        this.airportsService = airportsService;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) {

        List<Airport> listOfAirports = airportsService.getListOfAirports();
        if(!listOfAirports.isEmpty()) {
            airportsDAOService.saveAirports(listOfAirports);
        }

    }

}
