package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.User;
import com.gl.planesAndAirfileds.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Matchers.any;

@RunWith(SpringRunner.class)
@WebMvcTest(UserService.class)
public class UserServiceTest
{
   @MockBean
   private UserRepository userRepository;

   @MockBean
   private PasswordService passwordService;

   @Autowired
   private UserService userService;

   @Test
   public void saveTest(){
      userService.save(any(User.class), "pass");
   }

}
