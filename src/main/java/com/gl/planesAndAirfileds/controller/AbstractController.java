package com.gl.planesAndAirfileds.controller;

import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;

import java.util.Optional;

/**
 * Created by marek.sroga on 2017-03-06.
 */
public abstract class AbstractController {

    protected void addValidator(WebDataBinder binder, Class<?> clazz, Validator... validators) {
        Optional
                .ofNullable(binder.getTarget())
                .filter(notNullBinder -> clazz.isAssignableFrom(notNullBinder.getClass()))
                .ifPresent(o -> binder.addValidators(validators));
    }
}
