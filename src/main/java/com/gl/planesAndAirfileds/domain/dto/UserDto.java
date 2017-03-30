package com.gl.planesAndAirfileds.domain.dto;

import com.gl.planesAndAirfileds.domain.User;

import java.io.Serializable;

/**
 * Created by marek.sroga on 2017-03-30.
 */
public class UserDto implements Serializable
{
   public static final String FIELD_USER = "user";

   public static final String FIELD_PASSWORD = "password";

   public static final String FIELD_REPEATED_PASSWORD = "repeatedPassword";

   private User user;

   private String password;

   private String repeatedPassword;

   public User getUser()
   {
      return user;
   }

   public void setUser(User user)
   {
      this.user = user;
   }

   public String getPassword()
   {
      return password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }

   public String getRepeatedPassword()
   {
      return repeatedPassword;
   }

   public void setRepeatedPassword(String repeatedPassword)
   {
      this.repeatedPassword = repeatedPassword;
   }
}
