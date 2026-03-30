package com.khpallon.fujitsu.config;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.khpallon.fujitsu.enums.City;

/**
 * Component responsible for mapping cities to their corresponding weather station names.
 */

@Component
public class StationMapping {

    private final Map<City, String> cityToStation = Map.of(
        City.TARTU, "Tartu-Tõravere",
        City.TALLINN, "Tallinn-Harku",
        City.PARNU, "Pärnu"
    );

    public String getStationForCity(City city) {
        return cityToStation.get(city);
    }
}
