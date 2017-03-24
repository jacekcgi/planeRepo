package com.gl.planesAndAirfileds.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "airport")
public class Airport extends AbstractIdentifiableEntity {

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "iata_code")
    private String iataCode;

    @Column(name = "icao_code")
    private String icaoCode;

    @NotNull
    @Column(name = "latitude")
    private String latitude;

    @NotNull
    @Column(name = "longtitude")
    private String longitude;

    @Column(name = "altitude")
    private String altitude;

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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Airport)) {
            return false;
        }

        Airport airport = (Airport) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getName(), airport.getName())
                .append(getCity(), airport.getCity())
                .append(getCountry(), airport.getCountry())
                .append(getIataCode(), airport.getIataCode())
                .append(getIcaoCode(), airport.getIcaoCode())
                .append(getLatitude(), airport.getLatitude())
                .append(getLongitude(), airport.getLongitude())
                .append(getAltitude(), airport.getAltitude())
                .append(getTimezone(), airport.getTimezone())
                .append(getDaylightSavingTime(), airport.getDaylightSavingTime())
                .append(getTzDatabaseTimeZone(), airport.getTzDatabaseTimeZone())
                .append(getType(), airport.getType())
                .append(getSource(), airport.getSource())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getCity())
                .append(getCountry())
                .append(getIataCode())
                .append(getIcaoCode())
                .append(getLatitude())
                .append(getLongitude())
                .append(getAltitude())
                .append(getTimezone())
                .append(getDaylightSavingTime())
                .append(getTzDatabaseTimeZone())
                .append(getType())
                .append(getSource())
                .toHashCode();
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
