package com.khpallon.fujitsu.exception;

/**
 * Exception thrown when a vehicle type is forbidden for use under certain weather conditions.
 */

public class VehicleForbiddenException extends RuntimeException {
    public VehicleForbiddenException() {
        super("Usage of selected vehicle type is forbidden");
    }
}
