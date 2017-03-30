package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.api.Mappings;
import com.gl.planesAndAirfileds.domain.enums.AvailableLanguages;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by krzysztof.gonia on 3/21/2017.
 */
@RestController
public class LanguagesController extends AbstractController {

    @RequestMapping(value = Mappings.FIND_LANGUAGES, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public AvailableLanguages[] findLanguages() {
        return AvailableLanguages.values();
    }
}
