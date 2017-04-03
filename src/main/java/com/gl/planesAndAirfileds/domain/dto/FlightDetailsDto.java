package com.gl.planesAndAirfileds.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

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

    public static final String FIELD_CREATED_DATE = "created";

    public static final String FIELD_PLANE_NAME = "planeName";

    public static final String FIELD_PLANE_REGISTRATION = "planeRegistration";

    public static final String FIELD_AVERAGE_FUEL_CONSUMPTION = "averageFuelConsumption";

    public static final String FIELD_SOURCE_CITY = "sourceCity";

    public static final String FIELD_DESTINATION_CITY = "destinationCity";

    public FlightDetailsDto(double currentLatitude, double currentLongitude,
                            double destinationLatitude, double destinationLongitude,
                            double velocity, double distanceTraveled, double flightDistance, String flightRouteSid) {
        this(currentLatitude, currentLongitude, destinationLatitude, destinationLongitude, velocity, distanceTraveled,
                flightDistance, flightRouteSid, null, null, null, 0d, null, null);
    }

    public FlightDetailsDto(double currentLatitude, double currentLongitude,
                            double destinationLatitude, double destinationLongitude,
                            double velocity, double distanceTraveled, double flightDistance, String flightRouteSid,
                            LocalDateTime created, String planeName, String planeRegistration, double averageFuelConsumption, String sourceCity, String destinationCity) {
        this.currentLatitude = currentLatitude;
        this.currentLongitude = currentLongitude;
        this.destinationLatitude = destinationLatitude;
        this.destinationLongitude = destinationLongitude;
        this.distanceTraveled = distanceTraveled;
        this.flightDistance = flightDistance;
        this.velocity = velocity;
        this.flightRouteSid = flightRouteSid;
        this.created = created;
        this.planeName = planeName;
        this.planeRegistration = planeRegistration;
        this.averageFuelConsumption = averageFuelConsumption;
        this.sourceCity = sourceCity;
        this.destinationCity = destinationCity;
    }

    private double currentLatitude;

    private double currentLongitude;

    private double destinationLatitude;

    private double destinationLongitude;

    private double velocity;

    private String flightRouteSid;

    private double flightDistance;

    private double distanceTraveled;

    //time elapsed from creation and call for data in miliseconds
    private double timeElapsed;

    private LocalDateTime created;

    private String planeName;

    private String planeRegistration;

    private double averageFuelConsumption;

    private String sourceCity;

    private String destinationCity;

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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setFlightRouteSid(String flightRouteSid) {
        this.flightRouteSid = flightRouteSid;
    }

    public double getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(double timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public String getPlaneName() {
        return planeName;
    }

    public void setPlaneName(String planeName) {
        this.planeName = planeName;
    }

    public String getPlaneRegistration() {
        return planeRegistration;
    }

    public void setPlaneRegistration(String planeRegistration) {
        this.planeRegistration = planeRegistration;
    }

    public double getAverageFuelConsumption() {
        return averageFuelConsumption;
    }

    public void setAverageFuelConsumption(double averageFuelConsumption) {
        this.averageFuelConsumption = averageFuelConsumption;
    }

    public String getSourceCity() {
        return sourceCity;
    }

    public void setSourceCity(String sourceCity) {
        this.sourceCity = sourceCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }
}
