package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.dto.ErrorDto;
import com.gl.planesAndAirfileds.domain.dto.FieldErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by marek.sroga on 2017-03-06.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private MessageSource messageSource;

    @Autowired
    public ControllerExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public
    @ResponseBody
    Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        LOGGER.warn(e.getMessage(), e);
        return prepareErrorDto(e.getBindingResult());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public
    @ResponseBody
    Object handleBindException(BindException e) {
        LOGGER.warn(e.getMessage(), e);
        return prepareErrorDto(e.getBindingResult());
    }

    private ErrorDto prepareErrorDto(BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();

        Locale locale = LocaleContextHolder.getLocale();
        ErrorDto errorDto = new ErrorDto();
        String errorCode;
        ObjectError objectError = result.getGlobalError();
        if (objectError != null && !StringUtils.isEmpty(objectError.getCode())) {
            errorCode = objectError.getCode();
        }
        else {
            errorCode = "validation.exception";
        }
        errorDto.setMessage(messageSource.getMessage(errorCode, null, locale));

        List<FieldErrorDto> fieldErrorsDtos = new ArrayList<FieldErrorDto>();
        for (FieldError fieldError : fieldErrors) {
            String message = messageSource.getMessage(fieldError, locale);
            fieldErrorsDtos.add(new FieldErrorDto(fieldError.getField(), message));
        }
        errorDto.setFields(fieldErrorsDtos);

        return errorDto;
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public
    @ResponseBody
    Object handleThrowable(Throwable e) {
        LOGGER.error(e.getMessage(), e);
        Locale locale = LocaleContextHolder.getLocale();
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(messageSource.getMessage("throwable.exception", null, locale));
        return errorDto;
    }
}
