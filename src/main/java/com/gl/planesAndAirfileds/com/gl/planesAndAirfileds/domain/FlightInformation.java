package com.gl.planesAndAirfileds.com.gl.planesAndAirfileds.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class FlightInformation {
    @Id
    @GeneratedValue
    private Long id;
    private String gpsLatitude;
    private String gpsLongitude;
    private boolean isActualPosition;
    private Integer averageFuelConsumption;
    @Temporal(TemporalType.TIMESTAMP)
    private Date incomingTime;
    @ManyToOne
    private Plane plane;
}
