package com.khpallon.fujitsu.exception;

/**
 * Exception thrown when an unknown city is encountered during fee calculation.
 */

public class UnknownCityException extends RuntimeException {
    public UnknownCityException(String city) {
        super("Unknown city: " + city);
    }
}
