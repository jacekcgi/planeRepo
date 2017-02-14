package com.gl.planesAndAirfileds.com.gl.planesAndAirfileds.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by marcin.majka on 14/2/2017.
 */
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
