package com.gl.planesAndAirfileds.domain.dto;

import com.gl.planesAndAirfileds.domain.FlightRoute;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by krzysztof.gonia on 4/3/2017.
 */
public class FlightRouteDto extends FlightRouteUpdateDto {

    @NotBlank
    private String plane;

    @NotBlank
    private String source;

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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getIncomingDate() {
        return incomingDate;
    }
}
