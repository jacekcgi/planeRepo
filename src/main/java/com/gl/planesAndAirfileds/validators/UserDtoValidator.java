package com.gl.planesAndAirfileds.validators;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.domain.User;
import com.gl.planesAndAirfileds.domain.dto.UserDto;
import com.gl.planesAndAirfileds.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by marek.sroga on 2017-03-30.
 */
@Component
public class UserDtoValidator implements Validator
{
   @Autowired
   private UserService userService;

   @Override
   public boolean supports(Class<?> clazz)
   {
      return UserDto.class.isAssignableFrom(clazz);
   }

   @Override
   public void validate(Object target, Errors errors)
   {
      UserDto userDto = (UserDto) target;
      User user = userDto.getUser();
      String password = userDto.getPassword();
      String repeatedPassword = userDto.getRepeatedPassword();

      if (StringUtils.isEmpty(user.getSid())) {
         //creating
         if (StringUtils.isEmpty(password) || StringUtils.isEmpty(repeatedPassword) || !password.equals(repeatedPassword)) {
            errors.rejectValue(UserDto.FIELD_PASSWORD, "password.is.not.correct");
            errors.rejectValue(UserDto.FIELD_REPEATED_PASSWORD, "password.is.not.correct");
         }
      }

      if (userService.existUser(user.getLogin(), user.getSid())) {
         errors.rejectValue(User.FIELD_LOGIN, "entity.already.exist"); //here static field from entity
      }
   }
}
