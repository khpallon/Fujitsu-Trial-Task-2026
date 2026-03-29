package com.khpallon.fujitsu.service.rule;

import org.springframework.stereotype.Component;

import com.khpallon.fujitsu.enums.Vehicle;
import com.khpallon.fujitsu.model.WeatherEntity;

@Component
public class WindSpeedRule implements FeeRule {

    @Override
    public double apply(Vehicle vehicle, WeatherEntity weather) {

        double windSpeed = weather.getWindspeed();

        if (windSpeed >= 10 && windSpeed <= 20) return 0.5; 
        if (windSpeed > 20) throw new IllegalStateException("Usage of selected vehicle type is forbidden");

        return 0.0; 
    }
    
}
