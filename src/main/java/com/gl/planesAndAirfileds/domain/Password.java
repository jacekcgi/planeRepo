package com.gl.planesAndAirfileds.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by marek.sroga on 2017-03-29.
 */
@Entity
@Table(name = "password")
public class Password extends AbstractEntity {
    public static final String FIELD_PASSWORD = "password";

    public static final String FIELD_USER = "user";

    public static final int FIELD_PASSWORD_LENGTH = 256;

    public static final int FIELD_GUI_PASSWORD_LENGTH = 32;

    public static final int FIELD_PASSWORD_MIN_LENGTH = 4;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", unique = true)
    @NotNull
    private User user;

    @Column(nullable = false, length = FIELD_PASSWORD_LENGTH)
    @Length(min = FIELD_PASSWORD_MIN_LENGTH, max = FIELD_PASSWORD_LENGTH)
    @NotBlank
    private String password;

    public Password() {
    }

    public Password(String encoded, User user) {
        this.password = encoded;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
