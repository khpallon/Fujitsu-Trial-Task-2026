package com.khpallon.fujitsu.service.rule;

import com.khpallon.fujitsu.enums.Vehicle;
import com.khpallon.fujitsu.model.WeatherEntity;

/**
 * Interface for defining fee rules based on vehicle type and weather conditions.
 */

public interface FeeRule {
    double apply(Vehicle vehicle, WeatherEntity weather);
}
