package com.gl.planesAndAirfileds.components;

import com.gl.planesAndAirfileds.service.AirportsFileParserService;
import com.gl.planesAndAirfileds.service.AirportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AirPortsStorageWriter implements ApplicationRunner {

    private AirportsService airportsService;

    private AirportsFileParserService airportsFileParserService;

    public AirportsService getAirportsService() {
        return airportsService;
    }

    @Autowired
    public void setAirportsService(AirportsService airportsService) {
        this.airportsService = airportsService;
    }

    public AirportsFileParserService getAirportsFileParserService() {
        return airportsFileParserService;
    }

    @Autowired
    public void setAirportsFileParserService(AirportsFileParserService airportsFileParserService) {
        this.airportsFileParserService = airportsFileParserService;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) {

//        List<Airport> listOfAirports = airportsFileParserService.getListOfAirports();
//        if (!listOfAirports.isEmpty()) {
//            airportsService.saveAirports(listOfAirports); // temporary disable
//        }

    }

}
