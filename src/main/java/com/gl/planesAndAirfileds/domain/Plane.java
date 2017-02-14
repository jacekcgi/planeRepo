package com.gl.planesAndAirfileds.domain;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Plane {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date addDate;
}
