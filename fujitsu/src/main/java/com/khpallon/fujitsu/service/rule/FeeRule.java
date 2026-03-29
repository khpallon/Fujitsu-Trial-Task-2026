package com.khpallon.fujitsu.service.rule;

import com.khpallon.fujitsu.enums.Vehicle;
import com.khpallon.fujitsu.model.WeatherEntity;

public interface FeeRule {
    double apply(Vehicle vehicle, WeatherEntity weather);
}
