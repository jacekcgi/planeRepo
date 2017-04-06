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

    String GET_FLIGHT_DETAILS = "/get/flightDetails/{flightRoute_sid}";

    String FIND_FLIGHT_TRAIL = "/find/flightTrail/{flightRoute_sid}";

    //Airports controller

    String FIND_AIRPORTS = "/find/airports";

    String FIND_AIRPORTS_BY = "/find/airports";

    String GET_AIRPORT = "/getAirport/{airport_id}";

    // Languages

    String FIND_LANGUAGES = "/laguages";

    //flight routes

    String FIND_FLIGHT_ROUTES = "/find/flightRoutes";

    String CREATE_FLIGHT_ROUTES = "/flightRoute/create";

    String UPDATE_FLIGHT_ROUTES_DESTINATION = "/update/flightRoute/destination";

    String FIND_AIRPORTS_ONZOOM_LVL = "/findAirportsOnCurrZoom/{airport_zoomlevel}";
}
