package com.gl.planesAndAirfileds.domain;

import javax.persistence.*;

/**
 * Created by krzysztof.gonia on 3/8/2017.
 * <p>
 * Additional domain object for tests
 */
@Entity
@Table(name = "nested_entity")
public class NestedEntity extends AbstractEntity {

    public static final String FIELD_NESTED_ENTITY = "nestedEntity";

    public static final String FIELD_TEST_STRING = "testString";

    @Column(name = "test_string")
    private String testString;

    @ManyToOne
    @JoinColumn(name = "nested_entity_id")
    private NestedEntity nestedEntity;

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }

    public NestedEntity getNestedEntity() {
        return nestedEntity;
    }

    public void setNestedEntity(NestedEntity nestedEntity) {
        this.nestedEntity = nestedEntity;
    }
}
