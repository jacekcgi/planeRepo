package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.User;

/**
 * Created by marek.sroga on 2017-03-29.
 */
public interface UserRepository extends AbstractStatefulEntityRepository<User> {
    long countByLogin(String login, String ignoreSid);

    User getByLogin(String login);
}
