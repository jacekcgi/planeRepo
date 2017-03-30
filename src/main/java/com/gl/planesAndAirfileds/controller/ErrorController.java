package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.api.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

/**
 * Created by marek on 18.10.16.
 */
@RestController
public class ErrorController extends AbstractController {

    private MessageSource messageSource;

    @Autowired
    public ErrorController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(value = Mappings.ERROR_401, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView error401() {
        String a =null;
        if (a.equals("a"))
        {

        }
        return buildErrorModelAndView("error.401.title", "error.401.subtitle", "error.401.description");
    }

    @RequestMapping(value = Mappings.ERROR_403, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView error403() {
        return buildErrorModelAndView("error.403.title", "error.403.subtitle", "error.403.description");
    }

    @RequestMapping(value = Mappings.ERROR_404, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView error404() {
        return buildErrorModelAndView("error.404.title", "error.404.subtitle", "error.404.description");
    }

    @RequestMapping(value = Mappings.ERROR_500, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView error500() {
        return buildErrorModelAndView("error.500.title", "error.500.subtitle", "error.500.description");
    }

    private ModelAndView buildErrorModelAndView(String titleKey, String subtitleKey, String descriptionKey) {
        Locale locale = LocaleContextHolder.getLocale();
        ModelAndView model = new ModelAndView("errorPage");
        model.addObject("title", messageSource.getMessage(titleKey, null, locale));
        model.addObject("subtitle", messageSource.getMessage(subtitleKey, null, locale));
        model.addObject("description", messageSource.getMessage(descriptionKey, null, locale));
        return model;
    }
}
