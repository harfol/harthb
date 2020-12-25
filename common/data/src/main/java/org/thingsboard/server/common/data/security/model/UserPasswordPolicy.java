package org.thingsboard.server.common.data.security.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserPasswordPolicy implements Serializable {

    private Integer minimumLength;
    private Integer minimumUppercaseLetters;
    private Integer minimumLowercaseLetters;
    private Integer minimumDigits;
    private Integer minimumSpecialCharacters;

    private Integer passwordExpirationPeriodDays;
    private Integer passwordReuseFrequencyDays;

}
