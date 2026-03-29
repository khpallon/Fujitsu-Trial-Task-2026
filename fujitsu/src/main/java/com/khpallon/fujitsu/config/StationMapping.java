package com.khpallon.fujitsu.config;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class StationMapping {

    private final Map<String, String> cityToStation = Map.of(
        "tartu", "Tartu-Tõravere",
        "tallinn", "Tallinn-Harku",
        "pärnu", "Pärnu"
    );

    public String getStationForCity(String city) {
        return cityToStation.get(city.toLowerCase());
    }
}
