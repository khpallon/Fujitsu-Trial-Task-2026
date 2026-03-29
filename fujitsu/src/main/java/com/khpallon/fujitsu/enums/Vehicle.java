package com.khpallon.fujitsu.enums;

public enum Vehicle {
    CAR, SCOOTER, BIKE;

    public static Vehicle from(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }

        try {
            return Vehicle.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Unknown vehicle: " + value);
        }
    }
}
