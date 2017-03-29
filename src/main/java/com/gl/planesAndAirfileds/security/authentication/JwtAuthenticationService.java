package com.gl.planesAndAirfileds.security.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by marek.sobieraj on 2017-03-29.
 */
public interface JwtAuthenticationService {

    String createJwtToken(String username);

    Authentication parseJwtToken(HttpServletRequest request, String token)
            throws AuthenticationException;

    long getExpirationTokenTime();
}
