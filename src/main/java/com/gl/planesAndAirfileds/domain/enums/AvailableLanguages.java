package com.gl.planesAndAirfileds.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Locale;

/**
 * Created by krzysztof.gonia on 3/21/2017.
 */
@JsonFormat(shape= JsonFormat.Shape.OBJECT)
public enum AvailableLanguages{

    POLISH(new Locale("pl", "PL"), "pl"),
    ENGLISH(new Locale("en", "EN"), "gb");

    private Locale locale;

    //ISO 3166-1-alpha-2 code
    private String isoCode;

    AvailableLanguages(Locale locale, String isoCode) {
        this.locale = locale;
        this.isoCode = isoCode;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }
}
