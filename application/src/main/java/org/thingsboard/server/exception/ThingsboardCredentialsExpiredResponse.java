package org.thingsboard.server.exception;

import org.springframework.http.HttpStatus;
import org.thingsboard.server.common.data.exception.ThingsboardErrorCode;

public class ThingsboardCredentialsExpiredResponse extends ThingsboardErrorResponse {

    private final String resetToken;

    protected ThingsboardCredentialsExpiredResponse(String message, String resetToken) {
        super(message, ThingsboardErrorCode.CREDENTIALS_EXPIRED, HttpStatus.UNAUTHORIZED);
        this.resetToken = resetToken;
    }

    public static ThingsboardCredentialsExpiredResponse of(final String message, final String resetToken) {
        return new ThingsboardCredentialsExpiredResponse(message, resetToken);
    }

    public String getResetToken() {
        return resetToken;
    }
}
