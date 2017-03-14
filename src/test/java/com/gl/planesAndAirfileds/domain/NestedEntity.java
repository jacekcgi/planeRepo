package com.gl.planesAndAirfileds.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by krzysztof.gonia on 3/8/2017.
 * <p>
 * Additional domain object for tests
 */
@Entity
@Table(name = "nested_entityt")
public class NestedEntity extends AbstractEntity {

    public static final String FIELD_PLANE = "plane";

    public static final String FIELD_TEST_STRING = "testString";

    @Column(name = "test_string")
    private String testString;

    @ManyToOne(optional = false)
    @JoinColumn(name = "plane_id")
    @NotNull
    private Plane plane;

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }
}
