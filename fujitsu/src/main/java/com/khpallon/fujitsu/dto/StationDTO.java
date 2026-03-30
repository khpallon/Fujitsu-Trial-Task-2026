package com.khpallon.fujitsu.dto;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessType;

/**
 * Data Transfer Object for representing a weather station.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "station")
public class StationDTO {

    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "wmocode")
    private String wmocode;
    @XmlElement(name = "airtemperature")
    private Double airtemperature;
    @XmlElement(name = "windspeed")
    private Double windspeed;
    @XmlElement(name = "phenomenon")
    private String phenomenon;

    private String timestamp; // added to hold the timestamp from ObservationsDTO

    public StationDTO() {}


    // Getters

    public String getTimestamp() {
        return timestamp;
    }

    public String getName() {
        return name;
    }
    
    public String getWmocode() {
        return wmocode;
    }

    public Double getAirtemperature() {
        return airtemperature;
    }

    public Double getWindspeed() {
        return windspeed;
    }

    public String getPhenomenon() {
        return phenomenon;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
