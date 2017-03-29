package com.gl.planesAndAirfileds.domain.simulator;

import com.gl.planesAndAirfileds.domain.FlightPhase;
import com.gl.planesAndAirfileds.domain.FlightRoute;
import com.gl.planesAndAirfileds.domain.dto.FlightDetailsDto;

import java.time.LocalDateTime;

/**
 * Created by marek.sobieraj on 2017-03-27.
 */
public class GetFlightDetailsDto extends FlightDetailsDto {

    public static final String FIELD_SOURCE_LATITUDE = "sourceLatitude";

    public static final String FIELD_SOURCE_LONGITUDE = "sourceLongitude";

    public static final String FIELD_LAST_FLIGHT_PHASE = "lastFlightPhase";

    public static final String FIELD_LAST_CREATED_DATE = "lastCreatedDate";

    public static final String FIELD_START_FLIGHT_DATE = "startFlightDate";

    private double sourceLatitude;

    private double sourceLongitude;

    private FlightPhase lastFlightPhase;

    private LocalDateTime lastCreatedDate;

    private LocalDateTime startFlightDate;

    public GetFlightDetailsDto(double currentLatitude, double currentLongitude,
                               double destinationLatitude, double destinationLongitude,
                               double sourceLatitude, double sourceLongitude,
                               FlightPhase flightPhase,
                               LocalDateTime lastCreatedDate,
                               LocalDateTime startFlightDate,
                               double velocity, double distanceTraveled, double flightDistance, String flightRouteSid) {
        super(currentLatitude, currentLongitude, destinationLatitude, destinationLongitude, velocity, distanceTraveled,
                flightDistance,
                flightRouteSid);
        this.sourceLatitude = sourceLatitude;
        this.sourceLongitude = sourceLongitude;
        this.lastCreatedDate = lastCreatedDate;
        this.startFlightDate = startFlightDate;
        this.lastFlightPhase = flightPhase;
    }

    public GetFlightDetailsDto(FlightRoute flightRoute) {
        this(flightRoute.getSource().getLatitude(), flightRoute.getSource().getLongitude(),
                flightRoute.getDestination().getLatitude(), flightRoute.getDestination().getLongitude(),
                flightRoute.getSource().getLatitude(), flightRoute.getSource().getLongitude(),
                flightRoute.getFlightPhase(), LocalDateTime.now(), flightRoute.getStartDate(), 0d, 0d,
                flightRoute.getFlightDistance(), flightRoute.getSid());
    }

    public double getSourceLatitude() {
        return sourceLatitude;
    }

    public void setSourceLatitude(double sourceLatitude) {
        this.sourceLatitude = sourceLatitude;
    }

    public double getSourceLongitude() {
        return sourceLongitude;
    }

    public void setSourceLongitude(double sourceLongitude) {
        this.sourceLongitude = sourceLongitude;
    }

    public FlightPhase getLastFlightPhase() {
        return lastFlightPhase;
    }

    public void setLastFlightPhase(FlightPhase lastFlightPhase) {
        this.lastFlightPhase = lastFlightPhase;
    }

    public LocalDateTime getLastCreatedDate() {
        return lastCreatedDate;
    }

    public void setLastCreatedDate(LocalDateTime lastCreatedDate) {
        this.lastCreatedDate = lastCreatedDate;
    }

    public LocalDateTime getStartFlightDate() {
        return startFlightDate;
    }

    public void setStartFlightDate(LocalDateTime startFlightDate) {
        this.startFlightDate = startFlightDate;
    }
}
