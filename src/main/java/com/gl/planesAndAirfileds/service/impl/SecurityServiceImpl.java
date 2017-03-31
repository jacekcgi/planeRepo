package com.gl.planesAndAirfileds.service.impl;

import com.gl.planesAndAirfileds.domain.User;
import com.gl.planesAndAirfileds.service.SecurityService;
import com.gl.planesAndAirfileds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Created by marek.sobieraj on 2017-03-31.
 */
@Service("securityService")
public class SecurityServiceImpl implements SecurityService {

    private UserService userService;

    @Autowired
    public SecurityServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User getLoggedInUser() {

        UserDetails userDetails = getUserDetails();
        return userDetails != null ? userService.getByLogin(userDetails.getUsername()) : null;
    }

    protected UserDetails getUserDetails() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication authentication = ctx.getAuthentication();
        if (authentication == null) {
            return null;
        }
        return (UserDetails) authentication.getPrincipal();
    }
}
