package com.khpallon.fujitsu.service.rule;

import com.khpallon.fujitsu.enums.Vehicle;
import com.khpallon.fujitsu.exception.VehicleForbiddenException;
import com.khpallon.fujitsu.model.WeatherEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WindSpeedRuleTest {

    private final WindSpeedRule rule = new WindSpeedRule();

    private WeatherEntity weatherWith(double windSpeed) {
        WeatherEntity w = new WeatherEntity();
        w.setWindspeed(windSpeed);
        return w;
    }

    @Test
    void apply_returnsZeroWhenWindBelowTen() {
        double result = rule.apply(Vehicle.BIKE, weatherWith(5.0));
        assertEquals(0.0, result);
    }

    @Test
    void apply_returnsHalfWhenWindBetweenTenAndTwenty() {
        double result = rule.apply(Vehicle.CAR, weatherWith(15.0));
        assertEquals(0.5, result);
    }

    @Test
    void apply_throwsExceptionWhenWindAboveTwenty() {
        assertThrows(
                VehicleForbiddenException.class,
                () -> rule.apply(Vehicle.SCOOTER, weatherWith(25.0))
        );
    }
}
