package com.gl.planesAndAirfileds.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "flight_details")
public class FlightDetails extends AbstractEntity {

    public static final String FIELD_ACTUAL_POSITION = "actualPosition";

    public static final String FIELD_IS_LANDED = "isLanded";

    public static final String FIELD_PLANE = "plane";

    public static final String FIELD_FLIGHT_ROUTE = "flightRoute";

    public static final String FIELD_CREATED_DATE = "createdDate";

    @Column(name = "gps_latitude", nullable = false)
    @NotNull
    private double gpsLatitude;

    @Column(name = "gps_longitude", nullable = false)
    @NotNull
    private double gpsLongitude;

    @Column(name = "velocity", nullable = false)
    @NotNull
    private float velocity;

    @Column(name = "remaining_fuel")
    private Double remainingFuel;

    @Column(name = "distance_traveled", nullable = false)
    @NotNull
    private double distanceTraveled;

    @Column(name = "average_fuel_consumption")
    private Double averageFuelConsumption;

    @JoinColumn(name = "flight_route_id", nullable = false)
    @ManyToOne(optional = false)
    @NotNull
    private FlightRoute flightRoute;

    @Column(name = "created_date", nullable = false)
    @NotNull
    private LocalDateTime createdDate;

    @Column(name = "actual_position", nullable = false)
    private boolean actualPosition;

    public double getGpsLatitude() {
        return gpsLatitude;
    }

    public void setGpsLatitude(double gpsLatitude) {
        this.gpsLatitude = gpsLatitude;
    }

    public double getGpsLongitude() {
        return gpsLongitude;
    }

    public void setGpsLongitude(double gpsLongitude) {
        this.gpsLongitude = gpsLongitude;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public Double getRemainingFuel() {
        return remainingFuel;
    }

    public void setRemainingFuel(Double remainingFuel) {
        this.remainingFuel = remainingFuel;
    }

    public double getDistanceTraveled() {
        return distanceTraveled;
    }

    public void setDistanceTraveled(double distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    public Double getAverageFuelConsumption() {
        return averageFuelConsumption;
    }

    public void setAverageFuelConsumption(Double averageFuelConsumption) {
        this.averageFuelConsumption = averageFuelConsumption;
    }

    public FlightRoute getFlightRoute() {
        return flightRoute;
    }

    public void setFlightRoute(FlightRoute flightRoute) {
        this.flightRoute = flightRoute;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isActualPosition() {
        return actualPosition;
    }

    public void setActualPosition(boolean actualPosition) {
        this.actualPosition = actualPosition;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(";latitude: " + gpsLatitude);
        sb.append(";longitude: " + gpsLongitude);
        sb.append(";velocity:" + velocity);
        sb.append(";averageFuelConsumption " + averageFuelConsumption);
        sb.append(";remainingFuel " + remainingFuel);
        return sb.toString();
    }
}
