package com.gl.planesAndAirfileds.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "airport")
public class Airport extends AbstractIdentifiableEntity {

    public static final int FIELD_NAME_LENGTH = 255;

    public static final String FIELD_NAME = "name";

    public static final String FIELD_LATITUDE = "latitude";

    public static final String FIELD_LONGITUDE = "longitude";

    public static final String FIELD_IATA_CODE = "iataCode";

    public static final String FIELD_ICAO_CODE = "icaoCode";

    public static final String FIELD_COUNTRY = "country";

    public static final String FIELD_CITY = "city";

    @Column(name = "name", length = FIELD_NAME_LENGTH)
    @Length(max = FIELD_NAME_LENGTH)
    @NotBlank
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "iata_code")
    private String iataCode;

    @Column(name = "icao_code")
    private String icaoCode;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longtitude", nullable = false)
    private double longitude;

    @Column(name = "altitude", nullable = false)
    private double altitude;

    @Column(name = "timezone")
    private String timezone;

    @Column(name = "daylight_saving_time")
    private String daylightSavingTime;

    @Column(name = "tz_database_time_zone")
    private String tzDatabaseTimeZone;

    @Column(name = "type")
    private String type;

    @Column(name = "source")
    private String source;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getIcaoCode() {
        return icaoCode;
    }

    public void setIcaoCode(String icaoCode) {
        this.icaoCode = icaoCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getDaylightSavingTime() {
        return daylightSavingTime;
    }

    public void setDaylightSavingTime(String daylightSavingTime) {
        this.daylightSavingTime = daylightSavingTime;
    }

    public String getTzDatabaseTimeZone() {
        return tzDatabaseTimeZone;
    }

    public void setTzDatabaseTimeZone(String tzDatabaseTimeZone) {
        this.tzDatabaseTimeZone = tzDatabaseTimeZone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("city", city)
                .append("country", country)
                .append("iataCode", iataCode)
                .append("icaoCode", icaoCode)
                .append("latitude", latitude)
                .append("longitude", longitude)
                .append("altitude", altitude)
                .append("timezone", timezone)
                .append("daylightSavingTime", daylightSavingTime)
                .append("tzDatabaseTimeZone", tzDatabaseTimeZone)
                .append("type", type)
                .append("source", source)
                .toString();
    }
}
