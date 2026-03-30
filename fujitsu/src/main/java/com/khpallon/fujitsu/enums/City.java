package com.khpallon.fujitsu.enums;

import com.khpallon.fujitsu.exception.UnknownCityException;

/**
 * Enum representing supported cities for fee calculation.
 */

public enum City {
    TALLINN, TARTU, PARNU;

    public static City from(String value) {
        if (value == null) {
            throw new UnknownCityException(value);
        }

        try {
            return City.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new UnknownCityException(value);
        }
    }
}
