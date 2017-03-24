package com.gl.planesAndAirfileds.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by krzysztof.gonia on 3/17/2017.
 */
public class FlightDetailsDto implements Serializable {

    public static final String FIELD_UPDATE_TIME = "updateTime";

    public static final String FIELD_CURRENT_LATITUDE = "currentLatitude";

    public static final String FIELD_CURRENT_LONGITUDE = "currentLongitude";

    public static final String FIELD_DESTINATION_LATITUDE = "destinationLatitude";

    public static final String FIELD_DESTINATION_LONGITUDE = "destinationLongitude";

    public static final String FIELD_VELOCITY = "velocity";

    public static final String FIELD_FLIGHT_ROUTE_SID = "flightRouteSid";

    public FlightDetailsDto(double currentLatitude, double currentLongitude, double destinationLatitude,
                            double destinationLongitude, float velocity, String flightRouteSid) {
        this.currentLatitude = currentLatitude;
        this.currentLongitude = currentLongitude;
        this.destinationLatitude = destinationLatitude;
        this.destinationLongitude = destinationLongitude;
        this.velocity = velocity;
        this.flightRouteSid = flightRouteSid;
    }

    private LocalDateTime updateTime;

    private double currentLatitude;

    private double currentLongitude;

    private double destinationLatitude;

    private double destinationLongitude;

    private float velocity;

    private String flightRouteSid;

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
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

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public String getFlightRouteSid() {
        return flightRouteSid;
    }

    public void setFlightRouteSid(String flightRouteSid) {
        this.flightRouteSid = flightRouteSid;
    }
}
