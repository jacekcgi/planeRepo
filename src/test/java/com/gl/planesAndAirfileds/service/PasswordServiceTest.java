package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.User;
import com.gl.planesAndAirfileds.repository.PasswordRepository;
import com.gl.planesAndAirfileds.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Matchers.any;

/**
 * Created by marek.sroga on 2017-03-29.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PasswordService.class)
public class PasswordServiceTest
{
   @MockBean
   private PasswordRepository passwordRepository;

   @MockBean
   private PasswordEncoder passwordEncoder;

   @Autowired
   private PasswordService passwordService;

   @Test
   public void saveTest(){
      passwordService.save("pass", any(User.class));
   }

}
