package com.gl.planesAndAirfileds.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @Column(name = "gps_latitude")
    private Double gpsLatitude;

    @Column(name = "gps_longitude")
    private Double gpsLongitude;

    @Column(name = "course")
    private Double course;

    @Column(name = "velocity")
    private Float velocity;

    @Column(name = "is_actual_position")
    private boolean isActualPosition;

    @Column(name = "average_fuel_consumption")
    private Integer averageFuelConsumption;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "incoming_time")
    private Date incomingTime;

    @ManyToOne
    @JoinColumn(name = "plane_id", nullable = false)
    @NotNull
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
}
