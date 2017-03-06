package com.gl.planesAndAirfileds.validators;

import com.gl.planesAndAirfileds.domain.Plane;
import org.springframework.stereotype.Component;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by marek.sroga on 2017-03-06.
 */
@Component
public class PlaneTestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Plane.class.isAssignableFrom(clazz);
    }

    //example test to remove
    @Override
    public void validate(Object target, Errors errors) {
        Plane plane = (Plane) target;
        if (StringUtils.isEmpty(plane.getDescription())) {
            errors.rejectValue("description", "NotEmpty"); //here static field from entity
        }
    }
}
