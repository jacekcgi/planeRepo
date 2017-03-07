package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.components.AirPortsStorageWriter;
import com.gl.planesAndAirfileds.domain.Airports;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AirportsService {

    private static final Logger logger = LoggerFactory.getLogger(AirPortsStorageWriter.class);

    @Value("${airport.file.list}")
    private ClassPathResource classPathResource;

    public  List<Airports> getListOfAirports() {

            return parseAirportFile();
    }

    private  List<Airports> parseAirportFile() {

        try (Stream<String> stream = Files.lines(Paths.get(classPathResource.getURL().getFile()))) {

            return stream.map(lineText -> removeQuotationMarks(lineText))
                    .map(lineText -> new ArrayList<String>(Arrays.asList(lineText.split(","))))
                    .map(lineList -> createAirportData(lineList))
                    .collect(Collectors.toList());

        }  catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

      return  Collections.emptyList();
    }

    private String removeQuotationMarks(String lineText) {
        return lineText.replaceAll("\"", "");
    }

    private Airports createAirportData(ArrayList<String> lineList) {

        Airports airportData = new Airports();
        airportData.setId(Long.parseLong(lineList.get(0)));
        airportData.setName(lineList.get(1));
        airportData.setCity(lineList.get(2));
        airportData.setCountry(lineList.get(3));
        airportData.setIataCode(lineList.get(4));
        airportData.setIcaoCode(lineList.get(5));
        airportData.setLatitude(lineList.get(6));
        airportData.setLongitude(lineList.get(7));
        airportData.setAltitude(lineList.get(8));
        airportData.setTimezone(lineList.get(9));
        airportData.setDaylightSavingTime(lineList.get(10));
        airportData.setTzDatabaseTimeZone(lineList.get(11));
        airportData.setType(lineList.get(12));
        airportData.setSource(lineList.get(13));

        return airportData;
    }

}
