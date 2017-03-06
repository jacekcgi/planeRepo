package com.gl.planesAndAirfileds.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "flight_details")
public class FlightDetails extends AbstractEntity {
    public FlightDetails(Long id, Double gpsLatitude, Double gpsLongitude, Double course, Float velocity, boolean isActualPosition, Plane plane) {
        this.id = id;
        this.gpsLatitude = gpsLatitude;
        this.gpsLongitude = gpsLongitude;
        this.course = course;
        this.velocity = velocity;
        this.isActualPosition = isActualPosition;
        this.plane = plane;
    }
    public FlightDetails() {

    }

    private Long flightTime;
    private Double flightDistance;
    private Double gpsLatitude;
    private Double gpsLongitude;
    private Double course;
    private Float velocity;
    private Double remainingFuel;
    private boolean isActualPosition;
    private Integer averageFuelConsumption;
    private Double distanceTraveled;
    @Temporal(TemporalType.TIMESTAMP)
    private Date incomingTime;
    @ManyToOne
    private Plane plane;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getGpsLatitude() {
        return gpsLatitude;
    }

    public void setGpsLatitude(Double gpsLatitude) {
        this.gpsLatitude = gpsLatitude;
    }

    public Double getGpsLongitude() {
        return gpsLongitude;
    }

    public void setGpsLongitude(Double gpsLongitude) {
        this.gpsLongitude = gpsLongitude;
    }

    public boolean isActualPosition() {
        return isActualPosition;
    }

    public void setActualPosition(boolean actualPosition) {
        isActualPosition = actualPosition;
    }

    public Integer getAverageFuelConsumption() {
        return averageFuelConsumption;
    }

    public void setAverageFuelConsumption(Integer averageFuelConsumption) {
        this.averageFuelConsumption = averageFuelConsumption;
    }

    public Date getIncomingTime() {
        return incomingTime;
    }

    public void setIncomingTime(Date incomingTime) {
        this.incomingTime = incomingTime;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public Double getCourse() {
        return course;
    }

    public void setCourse(Double course) {
        this.course = course;
    }

    public Float getVelocity() {
        return velocity;
    }

    public void setVelocity(Float velocity) {
        this.velocity = velocity;
    }

    public Long getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(Long flightTime) {
        this.flightTime = flightTime;
    }

    public Double getFlightDistance() {
        return flightDistance;
    }

    public void setFlightDistance(Double flightDistance) {
        this.flightDistance = flightDistance;
    }

    public Double getRemainingFuel() {
        return remainingFuel;
    }

    public void setRemainingFuel(Double remainingFuel) {
        this.remainingFuel = remainingFuel;
    }

    public Double getDistanceTraveled() {
        return distanceTraveled;
    }

    public void setDistanceTraveled(Double distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("plane: " + plane.getId());
        sb.append(";latitude: " + gpsLatitude);
        sb.append(";longitude: " + gpsLongitude);
        sb.append(";course:" + course);
        sb.append(";velocity:" + velocity);
        sb.append(";flightDistance "+ flightDistance);
        sb.append(";distanceTraveled "+ distanceTraveled);
        sb.append(";averageFuelConsumption "+ averageFuelConsumption);
        sb.append(";remainingFuel "+ remainingFuel);
        sb.append(";flightTime "+ flightTime);
        return sb.toString();
    }
}
