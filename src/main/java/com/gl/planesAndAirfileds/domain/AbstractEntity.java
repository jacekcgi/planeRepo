package com.gl.planesAndAirfileds.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by krzysztof.gonia on 3/6/2017.
 */
@MappedSuperclass
public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue
    @JsonIgnore
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
