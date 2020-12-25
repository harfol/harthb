package org.thingsboard.server.common.data.security.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SecuritySettings implements Serializable {

    private UserPasswordPolicy passwordPolicy;

    private Integer maxFailedLoginAttempts;
    private String userLockoutNotificationEmail;
}
