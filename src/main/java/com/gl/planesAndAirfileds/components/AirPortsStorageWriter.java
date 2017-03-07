package com.gl.planesAndAirfileds.components;

import com.gl.planesAndAirfileds.domain.Airports;
import com.gl.planesAndAirfileds.service.AirportDAOService;
import com.gl.planesAndAirfileds.service.AirportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AirPortsStorageWriter implements ApplicationRunner {

    private AirportDAOService airportDAOService;

    private AirportsService airportsService;

    public AirportDAOService getAirportDAOService() {
        return airportDAOService;
    }

    @Autowired
    public void setAirportDAOService(AirportDAOService airportDAOService) {
        this.airportDAOService = airportDAOService;
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

        List<Airports> listOfAirports = airportsService.getListOfAirports();
        if(!listOfAirports.isEmpty()) {
            airportDAOService.saveAirports(listOfAirports);
        }

    }

}
