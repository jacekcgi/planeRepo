package com.gl.planesAndAirfileds.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Airports {

    @Id
    @NotNull
    private Long id;
    @NotNull
    private String name;
    private String city;
    private String country;
    private String iataCode;
    private String icaoCode;
    @NotNull
    private String latitude;
    @NotNull
    private String longitude;
    private String altitude;
    private String timezone;
    private String daylightSavingTime;
    private String tzDatabaseTimeZone;
    private String type;
    private String source;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        if (this == o) return true;
        if (!(o instanceof Airports)) return false;

        Airports airports = (Airports) o;

        if (!id.equals(airports.id)) return false;
        if (name != null ? !name.equals(airports.name) : airports.name != null) return false;
        if (city != null ? !city.equals(airports.city) : airports.city != null) return false;
        if (country != null ? !country.equals(airports.country) : airports.country != null) return false;
        if (iataCode != null ? !iataCode.equals(airports.iataCode) : airports.iataCode != null) return false;
        if (icaoCode != null ? !icaoCode.equals(airports.icaoCode) : airports.icaoCode != null) return false;
        if (latitude != null ? !latitude.equals(airports.latitude) : airports.latitude != null) return false;
        if (longitude != null ? !longitude.equals(airports.longitude) : airports.longitude != null) return false;
        if (altitude != null ? !altitude.equals(airports.altitude) : airports.altitude != null) return false;
        if (timezone != null ? !timezone.equals(airports.timezone) : airports.timezone != null) return false;
        if (daylightSavingTime != null ? !daylightSavingTime.equals(airports.daylightSavingTime) : airports.daylightSavingTime != null)
            return false;
        if (tzDatabaseTimeZone != null ? !tzDatabaseTimeZone.equals(airports.tzDatabaseTimeZone) : airports.tzDatabaseTimeZone != null)
            return false;
        if (type != null ? !type.equals(airports.type) : airports.type != null) return false;
        return source != null ? source.equals(airports.source) : airports.source == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (iataCode != null ? iataCode.hashCode() : 0);
        result = 31 * result + (icaoCode != null ? icaoCode.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (altitude != null ? altitude.hashCode() : 0);
        result = 31 * result + (timezone != null ? timezone.hashCode() : 0);
        result = 31 * result + (daylightSavingTime != null ? daylightSavingTime.hashCode() : 0);
        result = 31 * result + (tzDatabaseTimeZone != null ? tzDatabaseTimeZone.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Airports{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", iataCode='" + iataCode + '\'' +
                ", icaoCode='" + icaoCode + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", altitude='" + altitude + '\'' +
                ", timezone='" + timezone + '\'' +
                ", daylightSavingTime='" + daylightSavingTime + '\'' +
                ", tzDatabaseTimeZone='" + tzDatabaseTimeZone + '\'' +
                ", type='" + type + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
