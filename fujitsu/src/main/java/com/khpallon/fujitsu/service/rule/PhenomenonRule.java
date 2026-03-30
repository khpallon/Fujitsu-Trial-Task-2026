package com.khpallon.fujitsu.service.rule;

import org.springframework.stereotype.Component;

import com.khpallon.fujitsu.enums.Vehicle;
import com.khpallon.fujitsu.exception.VehicleForbiddenException;
import com.khpallon.fujitsu.model.WeatherEntity;

/**
 * Rule for calculating extra fees based on weather phenomena.
 */

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
            throw new VehicleForbiddenException();
        }
        return 0.0;
    }
    
}
