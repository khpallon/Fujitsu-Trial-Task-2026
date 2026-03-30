package com.khpallon.fujitsu.exception;

/**
 * Exception thrown when an unknown vehicle type is encountered during fee calculation.
 */

public class UnknownVehicleException extends RuntimeException {
    public UnknownVehicleException(String value) {
        super("Unknown vehicle: " + value);
    }
}