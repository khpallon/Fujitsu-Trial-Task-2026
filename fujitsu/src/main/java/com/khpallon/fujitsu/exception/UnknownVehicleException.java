package com.khpallon.fujitsu.exception;

import com.khpallon.fujitsu.enums.Vehicle;

/**
 * Exception thrown when an unknown vehicle type is encountered during fee calculation.
 */

public class UnknownVehicleException extends RuntimeException {
    public UnknownVehicleException(Vehicle vehicle) {
        super("Unknown vehicle: " + vehicle);
    }
}