package org.thingsboard.server.exception;

import org.springframework.http.HttpStatus;
import org.thingsboard.server.common.data.exception.ThingsboardErrorCode;

import java.util.Date;

public class ThingsboardErrorResponse {
    // HTTP Response Status Code
    private final HttpStatus status;

    // General Error message
    private final String message;

    // Error code
    private final ThingsboardErrorCode errorCode;

    private final Date timestamp;

    protected ThingsboardErrorResponse(final String message, final ThingsboardErrorCode errorCode, HttpStatus status) {
        this.message = message;
        this.errorCode = errorCode;
        this.status = status;
        this.timestamp = new java.util.Date();
    }

    public static ThingsboardErrorResponse of(final String message, final ThingsboardErrorCode errorCode, HttpStatus status) {
        return new ThingsboardErrorResponse(message, errorCode, status);
    }

    public Integer getStatus() {
        return status.value();
    }

    public String getMessage() {
        return message;
    }

    public ThingsboardErrorCode getErrorCode() {
        return errorCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
