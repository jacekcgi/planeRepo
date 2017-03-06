package com.gl.planesAndAirfileds.domain;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

/**
 * Created by krzysztof.gonia on 3/6/2017.
 */
@MappedSuperclass
public class AbstractStatefulEntity extends AbstractIdentifiableEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "state",nullable = false)
    private StateEnum stateEnum;

    public StateEnum getStateEnum() {
        return stateEnum;
    }

    public void setStateEnum(StateEnum stateEnum) {
        this.stateEnum = stateEnum;
    }
}
