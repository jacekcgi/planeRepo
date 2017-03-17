package com.gl.planesAndAirfileds.dto;

import com.gl.planesAndAirfileds.domain.FlightDetails;

import java.util.List;

/**
 * Created by krzysztof.gonia on 3/17/2017.
 */
public class FligthDetailsDto {

    private Long updateTime;

    List<FlightDetails> flightDetails;

    public FligthDetailsDto(Long updateTime, List<FlightDetails> flightDetails) {
        this.updateTime = updateTime;
        this.flightDetails = flightDetails;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public List<FlightDetails> getFlightDetails() {
        return flightDetails;
    }

    public void setFlightDetails(List<FlightDetails> flightDetails) {
        this.flightDetails = flightDetails;
    }
}
