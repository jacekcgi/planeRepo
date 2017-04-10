package com.gl.planesAndAirfileds.validators;

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
public class UserDtoValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;
        User user = userDto.getUser();

        boolean create = StringUtils.isEmpty(user.getSid());
        if (create) {
            //creating
            if (StringUtils.isBlank(userDto.getPassword())) {
                errors.rejectValue(UserDto.FIELD_PASSWORD, "NotEmpty");
            }
            if (StringUtils.isBlank(userDto.getRepeatedPassword())) {
                errors.rejectValue(UserDto.FIELD_REPEATED_PASSWORD, "NotEmpty");
            }
        }

        if (nonEmpty(userDto) && isNotEquals(userDto)) {
            errors.rejectValue(UserDto.FIELD_REPEATED_PASSWORD, "passwords.not.match");
        }

        if (create && userService.existUser(user.getLogin(), user.getSid())) {
            errors.rejectValue(UserDto.FIELD_USER + "." + User.FIELD_LOGIN,
                    "entity.already.exist"); //here static field from entity
        }
    }

    private boolean nonEmpty(UserDto userDto) {
        return StringUtils.isNotBlank(userDto.getPassword()) || StringUtils.isNotBlank(userDto.getRepeatedPassword());
    }

    private boolean isNotEquals(UserDto userDto) {
        return !StringUtils.equals(userDto.getPassword(), userDto.getRepeatedPassword());
    }
}
