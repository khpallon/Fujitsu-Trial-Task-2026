package com.khpallon.fujitsu.service.rule;

import com.khpallon.fujitsu.enums.Vehicle;
import com.khpallon.fujitsu.model.WeatherEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TemperatureRuleTest {

    private final TemperatureRule rule = new TemperatureRule();

    private WeatherEntity weatherWith(double temperature) {
        WeatherEntity w = new WeatherEntity();
        w.setAirtemperature(temperature);
        return w;
    }

    @Test
    void apply_returnsOneWhenTemperatureBelowMinusTen() {
        double result = rule.apply(Vehicle.BIKE, weatherWith(-15.0));
        assertEquals(1.0, result);
    }

    @Test
    void apply_returnsHalfWhenTemperatureBelowZero() {
        double result = rule.apply(Vehicle.CAR, weatherWith(-5.0));
        assertEquals(0.5, result);
    }

    @Test
    void apply_returnsZeroWhenTemperatureIsZeroOrAbove() {
        double result = rule.apply(Vehicle.SCOOTER, weatherWith(3.0));
        assertEquals(0.0, result);
    }
}
