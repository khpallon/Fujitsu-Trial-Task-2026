package com.khpallon.fujitsu.model;

import jakarta.persistence.*;

/**
 * Entity class representing weather data.
 */

@Entity
@Table(name = "weatherdata")
public class WeatherEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stationName;
    private String wmocode;
    private Double airtemperature;
    private Double windspeed;
    private String phenomenon;
    private String timestamp;
    
    public WeatherEntity() {}

    public WeatherEntity(String stationName, String wmocode, Double airtemperature, Double windspeed, String phenomenon, String timestamp) {
        this.stationName = stationName;
        this.wmocode = wmocode;
        this.airtemperature = airtemperature;
        this.windspeed = windspeed;
        this.phenomenon = phenomenon;
        this.timestamp = timestamp;
    }

    // Setters

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
    public void setWmocode(String wmocode) {
        this.wmocode = wmocode;
    }
    public void setAirtemperature(Double airtemperature) {
        this.airtemperature = airtemperature;
    }
    public void setWindspeed(Double windspeed) {
        this.windspeed = windspeed;
    }
    public void setPhenomenon(String phenomenon) {
        this.phenomenon = phenomenon;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    // Getters

    public Double getAirtemperature() {
        return airtemperature;
    }
    public String getStationName() {
        return stationName;
    }
    public String getPhenomenon() {
        return phenomenon;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public String getWmocode() {
        return wmocode;
    }
    public Double getWindspeed() {
        return windspeed;
    }   
}
