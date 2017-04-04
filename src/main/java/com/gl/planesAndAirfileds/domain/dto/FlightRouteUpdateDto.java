package com.gl.planesAndAirfileds.domain.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by marcin.majka on 4/4/2017.
 */
public class FlightRouteUpdateDto {

    private String sid;

    @NotBlank
    private String destination;


    public String getSid() {
        return sid;
    }

    public String getDestination() {
        return destination;
    }
}
