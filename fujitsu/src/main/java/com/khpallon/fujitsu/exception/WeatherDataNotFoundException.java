package com.khpallon.fujitsu.exception;

/**
 * Exception thrown when weather data for a specific station is not found.
 */

public class WeatherDataNotFoundException extends RuntimeException {
    public WeatherDataNotFoundException(String station) {
        super("No weather data available for station: " + station);
    }
    
}
