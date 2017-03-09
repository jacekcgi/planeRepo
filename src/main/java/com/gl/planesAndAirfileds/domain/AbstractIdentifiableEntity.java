package com.gl.planesAndAirfileds.domain;

import com.gl.planesAndAirfileds.domain.util.SidUtils;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

/**
 * Created by krzysztof.gonia on 3/6/2017.
 */
@MappedSuperclass
public class AbstractIdentifiableEntity extends AbstractEntity {

    public static final String FIELD_SID = "sid";

    public static final int FIELD_SID_LENGTH = 32;

    @Column(name = "sid", nullable = false, unique = true, length = FIELD_SID_LENGTH)
    @NotBlank
    private String sid;

    @PrePersist
    protected void onCreate() {
        if (sid == null) {
            sid = SidUtils.generate();
        }
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
