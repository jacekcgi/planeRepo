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
    private LocalDateTime startDate;

    @Column(name = "incoming_date", nullable = false)
    @NotNull
    private LocalDateTime incomingDate;

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
