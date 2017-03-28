package com.gl.planesAndAirfileds.domain.dto;

import java.io.Serializable;

/**
 * Created by krzysztof.gonia on 3/17/2017.
 */
public class FlightDetailsDto implements Serializable {

    public static final String FIELD_CURRENT_LATITUDE = "currentLatitude";

    public static final String FIELD_CURRENT_LONGITUDE = "currentLongitude";

    public static final String FIELD_DESTINATION_LATITUDE = "destinationLatitude";

    public static final String FIELD_DESTINATION_LONGITUDE = "destinationLongitude";

    public static final String FIELD_VELOCITY = "velocity";

    public static final String FIELD_DISTANCE_TRAVELED = "distanceTraveled";

    public static final String FIELD_FLIGHT_DISTANCE = "flightDistance";

    public static final String FIELD_FLIGHT_ROUTE_SID = "flightRouteSid";

    public FlightDetailsDto(double currentLatitude, double currentLongitude,
                            double destinationLatitude, double destinationLongitude,
                            double velocity, double distanceTraveled, double flightDistance, String flightRouteSid) {
        this.currentLatitude = currentLatitude;
        this.currentLongitude = currentLongitude;
        this.destinationLatitude = destinationLatitude;
        this.destinationLongitude = destinationLongitude;
        this.distanceTraveled = distanceTraveled;
        this.flightDistance = flightDistance;
        this.velocity = velocity;
        this.flightRouteSid = flightRouteSid;
    }

    private double currentLatitude;

    private double currentLongitude;

    private double destinationLatitude;

    private double destinationLongitude;

    private double velocity;

    private String flightRouteSid;

    private double flightDistance;

    private double distanceTraveled;

    public double getFlightDistance() {
        return flightDistance;
    }

    public void setFlightDistance(double flightDistance) {
        this.flightDistance = flightDistance;
    }

    public double getDistanceTraveled() {
        return distanceTraveled;
    }

    public void setDistanceTraveled(double distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    public double getCurrentLatitude() {
        return currentLatitude;
    }

    public void setCurrentLatitude(double currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public double getCurrentLongitude() {
        return currentLongitude;
    }

    public void setCurrentLongitude(double currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    public double getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(double destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    public double getDestinationLongitude() {
        return destinationLongitude;
    }

    public void setDestinationLongitude(double destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public String getFlightRouteSid() {
        return flightRouteSid;
    }

    public void setFlightRouteSid(String flightRouteSid) {
        this.flightRouteSid = flightRouteSid;
    }
}
