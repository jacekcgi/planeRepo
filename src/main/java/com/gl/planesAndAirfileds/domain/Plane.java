package com.gl.planesAndAirfileds.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Plane {

    public Plane() {

    }

    public Plane(Long id, String name, String registration, String description) {
        this.id = id;
        this.name = name;
        this.registration = registration;
        this.description = description;
    }

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String registration;
    @Column(columnDefinition = "TEXT")
    private String description;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date create_date;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date update_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }
}
