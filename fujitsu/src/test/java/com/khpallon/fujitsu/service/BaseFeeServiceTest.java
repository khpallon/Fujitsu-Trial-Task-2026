package com.khpallon.fujitsu.service;

import com.khpallon.fujitsu.enums.City;
import com.khpallon.fujitsu.enums.Vehicle;
import com.khpallon.fujitsu.exception.UnknownCityException;
import com.khpallon.fujitsu.exception.UnknownVehicleException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseFeeServiceTest {

    private final BaseFeeService service = new BaseFeeService();

    @Test
    void calculate_returnsCorrectFee_forTallinnCar() {
        double result = service.calculate(City.TALLINN, Vehicle.CAR);
        // CAR = 3.0, Tallinn multiplier = 2.0 → 3.0 + (0.5 * 2) = 4.0
        assertEquals(4.0, result);
    }

    @Test
    void calculate_returnsCorrectFee_forTartuScooter() {
        double result = service.calculate(City.TARTU, Vehicle.SCOOTER);
        // SCOOTER = 2.5, Tartu multiplier = 1.0 → 2.5 + 0.5 = 3.0
        assertEquals(3.0, result);
    }

    @Test
    void calculate_returnsCorrectFee_forParnuBike() {
        double result = service.calculate(City.PARNU, Vehicle.BIKE);
        // BIKE = 2.0, Pärnu multiplier = 0.0 → 2.0 + 0 = 2.0
        assertEquals(2.0, result);
    }

    @Test
    void calculate_throwsExceptionForUnknownCity() {
        assertThrows(
                UnknownCityException.class,
                () -> service.calculate(null, Vehicle.CAR)
        );
    }

    @Test
    void calculate_throwsExceptionForUnknownVehicle() {
        assertThrows(
                UnknownVehicleException.class,
                () -> service.calculate(City.TALLINN, null)
        );
    }
}
