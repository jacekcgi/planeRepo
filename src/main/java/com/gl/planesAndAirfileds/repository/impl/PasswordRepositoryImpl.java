package com.gl.planesAndAirfileds.repository.impl;

import com.gl.planesAndAirfileds.domain.Password;
import com.gl.planesAndAirfileds.repository.AbstractEntityRepository;
import com.gl.planesAndAirfileds.repository.PasswordRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by marek.sroga on 2017-03-29.
 */
@Repository("passwordRepository")
public class PasswordRepositoryImpl extends AbstractEntityRepositoryImpl<Password> implements PasswordRepository
{
}
