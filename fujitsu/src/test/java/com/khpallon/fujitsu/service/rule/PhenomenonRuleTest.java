package com.khpallon.fujitsu.service.rule;

import com.khpallon.fujitsu.enums.Vehicle;
import com.khpallon.fujitsu.exception.VehicleForbiddenException;
import com.khpallon.fujitsu.model.WeatherEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhenomenonRuleTest {

    private final PhenomenonRule rule = new PhenomenonRule();

    private WeatherEntity weatherWith(String phenomenon) {
        WeatherEntity w = new WeatherEntity();
        w.setPhenomenon(phenomenon);
        return w;
    }

    @Test
    void apply_returnsOneForSnow() {
        double result = rule.apply(Vehicle.BIKE, weatherWith("Light snow"));
        assertEquals(1.0, result);
    }

    @Test
    void apply_returnsOneForSleet() {
        double result = rule.apply(Vehicle.CAR, weatherWith("Sleet showers"));
        assertEquals(1.0, result);
    }

    @Test
    void apply_returnsHalfForRain() {
        double result = rule.apply(Vehicle.SCOOTER, weatherWith("Heavy rain"));
        assertEquals(0.5, result);
    }

    @Test
    void apply_throwsExceptionForGlaze() {
        assertThrows(
                VehicleForbiddenException.class,
                () -> rule.apply(Vehicle.BIKE, weatherWith("Glaze"))
        );
    }

    @Test
    void apply_throwsExceptionForHail() {
        assertThrows(
                VehicleForbiddenException.class,
                () -> rule.apply(Vehicle.CAR, weatherWith("Hailstorm"))
        );
    }

    @Test
    void apply_throwsExceptionForThunder() {
        assertThrows(
                VehicleForbiddenException.class,
                () -> rule.apply(Vehicle.SCOOTER, weatherWith("Thunder"))
        );
    }

    @Test
    void apply_returnsZeroForOtherPhenomena() {
        double result = rule.apply(Vehicle.BIKE, weatherWith("Cloudy"));
        assertEquals(0.0, result);
    }
}
