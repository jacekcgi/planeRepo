package com.gl.planesAndAirfileds.domain.dto;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by marek.sroga on 2017-03-06.
 */
public class ErrorDto implements Serializable {

    private String message;

    private List<FieldErrorDto> fields = Collections.emptyList();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FieldErrorDto> getFields() {
        return fields;
    }

    public void setFields(List<FieldErrorDto> fields) {
        this.fields = fields;
    }
}
