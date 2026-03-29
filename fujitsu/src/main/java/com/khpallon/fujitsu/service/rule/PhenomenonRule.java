package com.khpallon.fujitsu.service.rule;

import org.springframework.stereotype.Component;

import com.khpallon.fujitsu.enums.Vehicle;
import com.khpallon.fujitsu.model.WeatherEntity;

@Component
public class PhenomenonRule implements FeeRule {

    @Override
    public double apply(Vehicle vehicle, WeatherEntity weather) {
        String phenomenon = weather.getPhenomenon().toLowerCase();

        if (phenomenon.contains("snow") || phenomenon.contains("sleet")) {
            return 1.0;
        } else if (phenomenon.contains("rain")) {
            return 0.5;
        } else if (phenomenon.contains("glaze") || phenomenon.contains("hail") || phenomenon.contains("thunder")) {
            throw new IllegalStateException("Usage of selected vehicle type is forbidden");
        }
        return 0.0;
    }
    
}
