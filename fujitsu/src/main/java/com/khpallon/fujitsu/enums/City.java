package com.khpallon.fujitsu.enums;

public enum City {
    TALLINN, TARTU, PARNU;

    public static City from(String value) {
        if (value == null) {
            throw new IllegalArgumentException("City cannot be null");
        }

        try {
            return City.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Unknown city: " + value);
        }
    }
}
