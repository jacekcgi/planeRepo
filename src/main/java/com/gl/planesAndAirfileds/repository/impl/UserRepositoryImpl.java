package com.gl.planesAndAirfileds.repository.impl;

import com.gl.planesAndAirfileds.domain.User;
import com.gl.planesAndAirfileds.repository.AbstractStatefulEntityRepository;
import com.gl.planesAndAirfileds.repository.UserRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by marek.sroga on 2017-03-29.
 */
@Repository("userRepository")
public class UserRepositoryImpl extends AbstractStatefulEntityRepositoryImpl<User> implements UserRepository
{
}
