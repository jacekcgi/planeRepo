package com.gl.planesAndAirfileds.domain;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * Created by krzysztof.gonia on 3/6/2017.
 */
@MappedSuperclass
public class AbstractStatefulEntity extends AbstractIdentifiableEntity {

    public static String FIELD_ACTIVE = "active";

    @Enumerated(EnumType.STRING)
    @Column(name = "active", nullable = false)
    @NotNull
    private StateEnum active;

    public StateEnum getActive() {
        return active;
    }

    public void setActive(StateEnum active) {
        this.active = active;
    }
}
