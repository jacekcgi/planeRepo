package com.gl.planesAndAirfileds.service.impl;

import com.gl.planesAndAirfileds.domain.User;
import com.gl.planesAndAirfileds.repository.AbstractStatefulEntityRepository;
import com.gl.planesAndAirfileds.repository.UserRepository;
import com.gl.planesAndAirfileds.service.PasswordService;
import com.gl.planesAndAirfileds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by marek.sroga on 2017-03-29.
 */
@Service("userService")
public class UserServiceImpl extends AbstractStatefulEntityServiceImpl<User> implements UserService
{
   @Autowired
   private UserRepository userRepository;

   @Autowired
   private PasswordService passwordService;

   @Override
   protected AbstractStatefulEntityRepository<User> getRepository()
   {
      return userRepository;
   }

   @Override
   public User save(User entity) {
      throw new UnsupportedOperationException("Cannot save new user without setting password!");
   }

   @Override
   @Transactional
   public User save(User user, String password) {
      User saved = super.save(user);
      passwordService.save(password, saved);
      return saved;
   }

   @Override
   @Transactional(readOnly = true)
   public boolean existUser(String login, String ignoreSid)
   {
      return userRepository.countByLogin(login, ignoreSid) > 0;
   }

   @Override
   @Transactional(readOnly = true)
   public User getByLogin(String login) {
      return userRepository.getByLogin(login);
   }
}
