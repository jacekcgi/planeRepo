package com.gl.planesAndAirfileds.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "airport")
public class Airport extends AbstractIdentifiableEntity {

    public static final String FIELD_NAME = "name";

    public static final String FIELD_CITY = "city";

    public static final String FIELD_COUNTRY = "country";

    public static final String FIELD_IATA_CODE = "iataCode";

    public static final String FIELD_ICAO_CODE = "icaoCode";

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

        if (!id.equals(airport.id)) {
            return false;
        }
        if (name != null ? !name.equals(airport.name) : airport.name != null) {
            return false;
        }
        if (city != null ? !city.equals(airport.city) : airport.city != null) {
            return false;
        }
        if (country != null ? !country.equals(airport.country) : airport.country != null) {
            return false;
        }
        if (iataCode != null ? !iataCode.equals(airport.iataCode) : airport.iataCode != null) {
            return false;
        }
        if (icaoCode != null ? !icaoCode.equals(airport.icaoCode) : airport.icaoCode != null) {
            return false;
        }
        if (latitude != null ? !latitude.equals(airport.latitude) : airport.latitude != null) {
            return false;
        }
        if (longitude != null ? !longitude.equals(airport.longitude) : airport.longitude != null) {
            return false;
        }
        if (altitude != null ? !altitude.equals(airport.altitude) : airport.altitude != null) {
            return false;
        }
        if (timezone != null ? !timezone.equals(airport.timezone) : airport.timezone != null) {
            return false;
        }
        if (daylightSavingTime != null ? !daylightSavingTime
                .equals(airport.daylightSavingTime) : airport.daylightSavingTime != null) {
            return false;
        }
        if (tzDatabaseTimeZone != null ? !tzDatabaseTimeZone
                .equals(airport.tzDatabaseTimeZone) : airport.tzDatabaseTimeZone != null) {
            return false;
        }
        if (type != null ? !type.equals(airport.type) : airport.type != null) {
            return false;
        }
        return source != null ? source.equals(airport.source) : airport.source == null;
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
        return "Airport{" +
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
