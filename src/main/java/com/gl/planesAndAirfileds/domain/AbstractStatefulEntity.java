package com.gl.planesAndAirfileds.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * Created by krzysztof.gonia on 3/6/2017.
 */
@MappedSuperclass
public class AbstractStatefulEntity extends AbstractIdentifiableEntity {

    public static String FIELD_ACTIVE = "active";

    @Column(name = "active", nullable = false)
    @NotNull
    private boolean active = true;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
