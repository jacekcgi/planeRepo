package com.gl.planesAndAirfileds.domain.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by krzysztof.gonia on 4/3/2017.
 */
public class FlightRouteDto {

    @NotBlank
    private String plane;

    @NotBlank
    private String source;

    @NotBlank
    private String destination;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
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
