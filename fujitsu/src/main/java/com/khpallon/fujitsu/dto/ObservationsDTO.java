package com.khpallon.fujitsu.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Data Transfer Object for representing weather observations.
 */

@XmlRootElement(name = "observations")
@XmlAccessorType(XmlAccessType.FIELD)
public class ObservationsDTO {

    @XmlAttribute(name = "timestamp")
    private String timestamp;

    @XmlElement(name = "station")
    private List<StationDTO> stations;

    public ObservationsDTO() {}

    
    public List<StationDTO> getStations() {
        return stations;
    }

    public String getTimestamp() {
        return timestamp;
    }

}