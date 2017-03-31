package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.Password;
import com.gl.planesAndAirfileds.domain.User;

/**
 * Created by marek.sroga on 2017-03-29.
 */
public interface PasswordRepository extends AbstractEntityRepository<Password> {
    Password getByUser(User user);
}
