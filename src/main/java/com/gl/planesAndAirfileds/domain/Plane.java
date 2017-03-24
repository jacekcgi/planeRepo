package com.gl.planesAndAirfileds.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "plane")
public class Plane extends AbstractIdentifiableEntity {

    public static final String FIELD_DESCRIPTION = "description";

    public static final String FIELD_REGISTRATION = "registration";

    public static final String FIELD_NAME = "name";

    @Column(name = "name")
    @NotEmpty
    private String name;

    @Column(name = "registration", unique = true)
    @NotEmpty
    private String registration;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "create_date", nullable = false)
    @NotNull(groups = Validation.PlaneValidate.class)
    private LocalDateTime createDate;

    @Column(name = "updateDate", nullable = false)
    @NotNull(groups = Validation.PlaneValidate.class)
    private LocalDateTime updateDate;

    @PrePersist
    public void onPersist() {
        updateDate = createDate = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updateDate = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }
}
