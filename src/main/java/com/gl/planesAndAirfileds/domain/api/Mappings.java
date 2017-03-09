package com.gl.planesAndAirfileds.domain.api;

/**
 * Created by marek.sroga on 2017-03-06.
 */
public interface Mappings {
    //PlanesController
    String CREATE_PLANE = "/plane";

    String FIND_PLANES = "/planeList";

    String FIND_PLANE_IDS = "/planeIdList";

    //FlightDetailsController
    String GET_CURRENT_TIME = "/getCurrentTime";

    String FIND_CURRENT_POSITIONS = "/planeLocation";

    String GET_CURRENT_POSITION = "/planeLocation/{id}";

    String GET_FLIGHT_DETAILS = "/flightDetails/{plane_id}";

    String POST_FLIGHT_DETAILS = "/flightDetails";

    //Airports controller

    String FIND_AIRPORTS = "/findAirports";

    String GET_AIRPORT = "/getAirport/{airport_id}";
}
