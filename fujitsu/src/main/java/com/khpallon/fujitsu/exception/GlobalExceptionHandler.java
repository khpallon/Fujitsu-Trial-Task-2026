package com.khpallon.fujitsu.exception;

import com.khpallon.fujitsu.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler to catch and respond to custom exceptions with appropriate HTTP status codes and messages.
 * @param ex the exception that was thrown
 * @return a ResponseEntity containing an ErrorResponse with the error message and HTTP status code
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnknownCityException.class)
    public ResponseEntity<ErrorResponse> handleUnknownCity(UnknownCityException ex) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(UnknownVehicleException.class)
    public ResponseEntity<ErrorResponse> handleUnknownVehicle(UnknownVehicleException ex) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(VehicleForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleForbiddenVehicle(VehicleForbiddenException ex) {
        return build(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler(WeatherDataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleWeatherNotFound(WeatherDataNotFoundException ex) {
        return build(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // Fallback for any other unexpected exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error occurred");
    }

    private ResponseEntity<ErrorResponse> build(HttpStatus status, String message) {
        return new ResponseEntity<>(new ErrorResponse(message, status.value()), status);
    }
}
