package com.khpallon.fujitsu.service.rule;

import org.springframework.stereotype.Component;

import com.khpallon.fujitsu.enums.Vehicle;
import com.khpallon.fujitsu.model.WeatherEntity;

/**
 * Rule for calculating extra fees based on temperature conditions.
 */

@Component
public class TemperatureRule implements FeeRule {

    @Override
    public double apply(Vehicle vehicle, WeatherEntity weather) {

        double temperature = weather.getAirtemperature();

        if (temperature < -10) return 1.0; 
        if (temperature < 0) return 0.5;

        return 0.0; 
    }
    
}
