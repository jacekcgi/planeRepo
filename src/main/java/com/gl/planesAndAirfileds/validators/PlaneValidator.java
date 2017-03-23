package com.gl.planesAndAirfileds.validators;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by marek.sroga on 2017-03-06.
 */
@Component
public class PlaneValidator implements Validator {

    private final PlaneService planeService;

    @Autowired
    public PlaneValidator(PlaneService planeService) {
        this.planeService = planeService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Plane.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Plane plane = (Plane) target;
        if (planeService.existRegistration(plane.getRegistration(), plane.getSid())) {
            errors.rejectValue(Plane.FIELD_REGISTRATION, "entity.already.exist"); //here static field from entity
        }
    }
}
