package com.gl.planesAndAirfileds.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by krzysztof.gonia on 3/23/2017.
 */
@Entity
@Table(name = "flight_route")
public class FlightRoute extends AbstractIdentifiableEntity {

    public static final String FIELD_START_DATE = "startDate";

    public static final String FIELD_INCOMING_DATE = "incomingDate";

    public static final String FIELD_DESTINATION = "destination";

    public static final String FIELD_FLIGHT_DISTANCE = "flightDistance";

    public static final String FIELD_FLIGHT_PHASE = "flightPhase";

    public static final String FIELD_SOURCE = "source";

    @ManyToOne(optional = false)
    @JoinColumn(name = "source_id", nullable = false)
    @NotNull
    private Airport source;

    @ManyToOne(optional = false)
    @JoinColumn(name = "destination_id", nullable = false)
    @NotNull
    private Airport destination;

    @ManyToOne(optional = false)
    @JoinColumn(name = "plane_id", nullable = false)
    @NotNull
    private Plane plane;

    @Column(name = "start_date", nullable = false)
    @NotNull
    private LocalDateTime startDate; // datetime to start flight

    @Column(name = "incoming_date", nullable = false)
    @NotNull
    private LocalDateTime incomingDate; // planned date time to finish flight

    @Column(name = "landed_date")
    private LocalDateTime landedDate; // landed date time

    @Column(name = "flight_phase", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private FlightPhase flightPhase;

    //in km
    @Column(name = "flight_distance", nullable = false)
    @NotNull
    private double flightDistance;

    public LocalDateTime getLandedDate() {
        return landedDate;
    }

    public void setLandedDate(LocalDateTime landedDate) {
        this.landedDate = landedDate;
    }

    public FlightPhase getFlightPhase() {
        return flightPhase;
    }

    public void setFlightPhase(FlightPhase flightPhase) {
        this.flightPhase = flightPhase;
    }

    public double getFlightDistance() {
        return flightDistance;
    }

    public void setFlightDistance(double flightDistance) {
        this.flightDistance = flightDistance;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getIncomingDate() {
        return incomingDate;
    }

    public void setIncomingDate(LocalDateTime incomingDate) {
        this.incomingDate = incomingDate;
    }

    public Airport getSource() {
        return source;
    }

    public void setSource(Airport source) {
        this.source = source;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }
}
