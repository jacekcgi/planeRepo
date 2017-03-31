package com.gl.planesAndAirfileds.service.impl;

import com.gl.planesAndAirfileds.domain.Password;
import com.gl.planesAndAirfileds.domain.User;
import com.gl.planesAndAirfileds.repository.AbstractEntityRepository;
import com.gl.planesAndAirfileds.repository.PasswordRepository;
import com.gl.planesAndAirfileds.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by marek.sroga on 2017-03-29.
 */
@Service("passwordService")
public class PasswordServiceImpl extends AbstractEntityServiceImpl<Password> implements PasswordService
{
   @Autowired
   private PasswordRepository passwordRepository;

   @Autowired
   private PasswordEncoder passwordEncoder;

   @Override
   protected AbstractEntityRepository<Password> getRepository()
   {
      return passwordRepository;
   }

   @Override
   @Transactional
   public Password save(String password, User user)
   {
      String encoded = passwordEncoder.encode(password);
      return super.save(new Password(encoded, user));
   }

   @Override
   @Transactional(readOnly = true)
   public Password getByUser(User user) {
      return passwordRepository.getByUser(user);
   }
}
