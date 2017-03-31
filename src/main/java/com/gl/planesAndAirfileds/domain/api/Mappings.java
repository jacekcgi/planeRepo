package com.gl.planesAndAirfileds.domain.api;

/**
 * Created by marek.sroga on 2017-03-06.
 */
public interface Mappings {

    String SIMULATOR_PREFIX = "/simulator";

    String GET_PLANE = "/get/plane/{sid}";

    String FIND_PLANES = "/find/planes";

    String POST_FLIGHT_DETAILS = SIMULATOR_PREFIX + "/flightDetails";

    //PlanesController
    String CREATE_PLANE = "/plane";

    String CURRENT_FLIGHTS = SIMULATOR_PREFIX + "/currentFlights";

    //FlightDetailsController
    String FIND_CURRENT_POSITIONS = "/allPlanesLocation";

    String GET_SEND_PLANE_TO_POSITION = "/sendPlaneToPosition/{sid}/{latitude}/{longitude}";

    String GET_CURRENT_POSITION = "/onePlaneLocation/{sid}";

    String GET_FLIGHT_DETAILS = "/flightDetails/{plane_sid}";

    //Airports controller

    String FIND_AIRPORTS = "/findAirports";

    String GET_AIRPORT = "/getAirport/{airport_id}";

    //MaxDistanceController

    String MAX_DISTANCE_FOR_PLANE = "/planeMaxDistance/{plane_sid}";

    // Languages

    String FIND_LANGUAGES = "/laguages";

    //flight routes

    String FIND_FLIGHT_ROUTES = "/find/flightRoutes";

    // authentication
    String AUTHENTICATION_API = "/login";

    //users
    String FIND_USERS = "/find/users";

    String SAVE_USER = "/save/user";

    String GET_USER = "/get/user/{sid}";

    String UPDATE_USER = "/update/user";
}
