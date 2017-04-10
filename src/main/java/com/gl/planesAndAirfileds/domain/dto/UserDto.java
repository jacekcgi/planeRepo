package com.gl.planesAndAirfileds.domain.dto;

import com.gl.planesAndAirfileds.domain.Password;
import com.gl.planesAndAirfileds.domain.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by marek.sroga on 2017-03-30.
 */
public class UserDto implements Serializable
{
   public static final String FIELD_USER = "user";

   public static final String FIELD_PASSWORD = "password";

   public static final String FIELD_REPEATED_PASSWORD = "repeatedPassword";

   @Valid
   @NotNull
   private User user;

   @Length(min = Password.FIELD_PASSWORD_MIN_LENGTH, max = Password.FIELD_GUI_PASSWORD_LENGTH)
   private String password;

   @Length(min = Password.FIELD_PASSWORD_MIN_LENGTH, max = Password.FIELD_GUI_PASSWORD_LENGTH)
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
