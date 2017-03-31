package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.Password;
import com.gl.planesAndAirfileds.domain.User;

/**
 * Created by marek.sroga on 2017-03-29.
 */
public interface PasswordService extends AbstractEntityService<Password> {
    Password save(String password, User user);
}
