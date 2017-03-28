package com.gl.planesAndAirfileds.domain.api;

/**
 * Created by marek.sroga on 2017-03-06.
 */
public interface Mappings {
    //PlanesController
    String CREATE_PLANE = "/plane";

    String GET_PLANE = "/get/plane/{sid}";

    String FIND_PLANES = "/find/planes";

    String FIND_PLANE_SIDS = "/planeSidList";

    //FlightDetailsController
    String GET_CURRENT_TIME = "/getCurrentTime";

    String FIND_CURRENT_POSITIONS_UPDATE = "/allPlanesLocation/{last_update}";

    String GET_CURRENT_POSITION_UPDATE = "/onePlaneLocation/{sid}/{last_update}";

    String FIND_CURRENT_POSITIONS = "/allPlanesLocation";

    String GET_SEND_PLANE_TO_POSITION = "/sendPlaneToPosition/{sid}/{latitude}/{longitude}";

    String GET_CURRENT_POSITION = "/onePlaneLocation/{sid}";

    String GET_FLIGHT_DETAILS = "/flightDetails/{plane_sid}";

    String POST_FLIGHT_DETAILS = "/flightDetails";

    //Airports controller

    String FIND_AIRPORTS = "/findAirports";

    String GET_AIRPORT = "/getAirport/{airport_id}";

    // Languages

    String FIND_LANGUAGES = "/laguages";

    //flight routes

    String FIND_FLIGHT_ROUTES = "/find/flightRoutes";
}
