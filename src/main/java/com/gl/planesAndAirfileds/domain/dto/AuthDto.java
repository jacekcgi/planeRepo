package com.gl.planesAndAirfileds.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by marek.sobieraj on 2017-03-29.
 */
public class AuthDto implements Serializable {

    private String token;

    private String name;

    private LocalDateTime loginDate;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(LocalDateTime loginDate) {
        this.loginDate = loginDate;
    }
}
