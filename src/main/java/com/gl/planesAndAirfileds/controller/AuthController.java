package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.api.Mappings;
import com.gl.planesAndAirfileds.domain.dto.AuthDto;
import com.gl.planesAndAirfileds.security.authentication.JwtAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by marek.sobieraj on 2017-03-29.
 */
@RestController
public class AuthController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping(value = Mappings.AUTHENTICATION_API, method = RequestMethod.POST)
    public AuthDto auth(@RequestHeader("login") String login, @RequestHeader("password") String password,
                        HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(login, password));
            if (authentication.isAuthenticated() && authentication instanceof JwtAuthenticationToken) {
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(authentication);
                SecurityContextHolder.setContext(securityContext);

                // TODO
//                User user = securityService.getLoggedInUser();

                JwtAuthenticationToken jwtAuthentication = (JwtAuthenticationToken) authentication;
                String token = jwtAuthentication.getToken();
                response.setStatus(HttpStatus.OK.value());
                AuthDto authDto = new AuthDto();
                authDto.setName(jwtAuthentication.getName());
                authDto.setToken(token);
                return authDto;
            }
            else {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return null;
            }
        }
        catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("unnable to authenticate", e);
            }
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return null;
        }
    }
}
