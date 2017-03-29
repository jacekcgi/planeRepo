package com.gl.planesAndAirfileds.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Created by marek.sobieraj on 2017-03-28.
 */
public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final String token;

    private final int tokenExpiryTimeInSeconds;

    public JwtAuthenticationToken(Object principal, String token) {
        super(principal, null);
        this.token = token;
        this.tokenExpiryTimeInSeconds = -1;
    }

    public String getToken() {
        return token;
    }

    public int getTokenExpiryTimeInSeconds() {
        return tokenExpiryTimeInSeconds;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
