package com.gl.planesAndAirfileds.security.filter;

import com.gl.planesAndAirfileds.controller.AuthController;
import com.gl.planesAndAirfileds.security.TokenAuthenticationService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by marek.sobieraj on 2017-03-29.
 */
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = getToken(request);
        if (token != null) {
            Authentication authentication = TokenAuthenticationService.parseJwtToken(request, token);
            Authentication authResult = authenticationManager.authenticate(authentication);
            if (authResult != null) {
                LOGGER.debug("Authentication success: " + authResult.toString());
            }
            SecurityContextHolder.getContext().setAuthentication(authResult);
        }
        filterChain.doFilter(request, response);
    }

    //    we can change it to take from cookie
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.isNotBlank(token)) {
            LOGGER.debug("Authorization token: " + token);
            return token;
        }
        return null;
    }
}
