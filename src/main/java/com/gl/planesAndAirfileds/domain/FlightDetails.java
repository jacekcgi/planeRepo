package com.gl.planesAndAirfileds.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class FlightDetails {
    public FlightDetails(Long id, String gpsLatitude, String gpsLongitude, Long course, Float velocity, boolean isActualPosition, Plane plane) {
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

    @Id
    @GeneratedValue
    private Long id;
    private String gpsLatitude;
    private String gpsLongitude;
    private Long course;
    private Float velocity;
    private boolean isActualPosition;
    private Integer averageFuelConsumption;
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

    public String getGpsLatitude() {
        return gpsLatitude;
    }

    public void setGpsLatitude(String gpsLatitude) {
        this.gpsLatitude = gpsLatitude;
    }

    public String getGpsLongitude() {
        return gpsLongitude;
    }

    public void setGpsLongitude(String gpsLongitude) {
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

    public Long getCourse() {
        return course;
    }

    public void setCourse(Long course) {
        this.course = course;
    }

    public Float getVelocity() {
        return velocity;
    }

    public void setVelocity(Float velocity) {
        this.velocity = velocity;
    }
}
