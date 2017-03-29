package com.gl.planesAndAirfileds.security.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by marek.sobieraj on 2017-03-28.
 */
public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final String token;

    private final long tokenExpiryTimeInSeconds;

    public JwtAuthenticationToken(Object principal, String token) {
        super(principal, null);
        this.token = token;
        this.tokenExpiryTimeInSeconds = -1;
    }

    public JwtAuthenticationToken(Object principal, String token, long tokenExpiryTimeInSeconds,
                                  Collection<? extends GrantedAuthority> authorities)
    {
        super(principal, null, authorities);
        this.token = token;
        this.tokenExpiryTimeInSeconds = tokenExpiryTimeInSeconds;
    }

    public String getToken() {
        return token;
    }

    public long getTokenExpiryTimeInSeconds() {
        return tokenExpiryTimeInSeconds;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
