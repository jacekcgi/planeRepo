package com.gl.planesAndAirfileds.security.provider;

import com.gl.planesAndAirfileds.domain.Password;
import com.gl.planesAndAirfileds.domain.User;
import com.gl.planesAndAirfileds.service.PasswordService;
import com.gl.planesAndAirfileds.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

/**
 * Created by marek.sobieraj on 2017-03-28.
 */

public class AirplaneUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AirplaneUserDetailsService.class);

    private UserService userService;

    private PasswordService passwordService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userService.getByLogin(login);
        }
        catch (EmptyResultDataAccessException e) {
            LOGGER.error("user '" + login + "' not found", e);
        }
        if (user == null || !user.isActive()) {
            throw new UsernameNotFoundException("user '" + login + "' not found or is inactive!");
        }
        Password password = passwordService.getByUser(user);
        if (password == null || StringUtils.isBlank(password.getPassword())) {
            throw new UsernameNotFoundException("user password '" + login + "' not found!");
        }
        return new org.springframework.security.core.userdetails.User(login, password.getPassword(),
                Collections.emptyList());
    }

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public PasswordService getPasswordService() {
        return passwordService;
    }

    @Autowired
    public void setPasswordService(PasswordService passwordService) {
        this.passwordService = passwordService;
    }
}
