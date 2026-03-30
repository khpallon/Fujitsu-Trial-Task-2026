package com.khpallon.fujitsu.enums;

import com.khpallon.fujitsu.exception.UnknownVehicleException;

/**
 * Enum representing supported vehicle types for fee calculation.
 */

public enum Vehicle {
    CAR, SCOOTER, BIKE;

    public static Vehicle from(String value) {
        if (value == null) {
            throw new UnknownVehicleException(value);
        }

        try {
            return Vehicle.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new UnknownVehicleException(value);
        }
    }
}
