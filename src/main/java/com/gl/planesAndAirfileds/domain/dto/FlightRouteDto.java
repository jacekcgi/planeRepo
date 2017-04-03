package com.gl.planesAndAirfileds.domain.dto;

import java.time.LocalDateTime;

/**
 * Created by krzysztof.gonia on 4/3/2017.
 */
public class FlightRouteDto {

    private String plane;

    private String source;

    private String destination;

    private LocalDateTime startDate;

    private LocalDateTime incomingDate;

    public String getPlane() {
        return plane;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getIncomingDate() {
        return incomingDate;
    }
}
