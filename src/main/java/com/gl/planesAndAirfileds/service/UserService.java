package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by marek.sroga on 2017-03-29.
 */
public interface UserService extends AbstractStatefulEntityService<User>
{
   User save(User user, String password);

   boolean existUser(String login, String sid);
}
