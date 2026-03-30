package com.khpallon.fujitsu.exception;

import com.khpallon.fujitsu.enums.City;

/**
 * Exception thrown when an unknown city is encountered during fee calculation.
 */

public class UnknownCityException extends RuntimeException {
    public UnknownCityException(City city) {
        super("Unknown city: " + city);
    }
}
