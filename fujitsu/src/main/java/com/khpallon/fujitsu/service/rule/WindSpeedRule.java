package com.khpallon.fujitsu.service.rule;

import org.springframework.stereotype.Component;

import com.khpallon.fujitsu.enums.Vehicle;
import com.khpallon.fujitsu.exception.VehicleForbiddenException;
import com.khpallon.fujitsu.model.WeatherEntity;

/**
 * Rule for calculating extra fees based on wind speed conditions.
 */

@Component
public class WindSpeedRule implements FeeRule {

    @Override
    public double apply(Vehicle vehicle, WeatherEntity weather) {

        double windSpeed = weather.getWindspeed();

        if (windSpeed >= 10 && windSpeed <= 20) return 0.5; 
        if (windSpeed > 20) throw new VehicleForbiddenException();

        return 0.0; 
    }
    
}
