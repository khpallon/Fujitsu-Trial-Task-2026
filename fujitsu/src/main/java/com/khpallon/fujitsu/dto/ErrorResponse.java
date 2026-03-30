package com.khpallon.fujitsu.dto;

/**
 * DTO for error responses, containing an error message and the corresponding HTTP status code.
 * @param message the error message to be returned in the response
 * @param status the HTTP status code associated with the error
 */

public class ErrorResponse {
    private String message;
    private int status;

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() { return message; }
    public int getStatus() { return status; }

}