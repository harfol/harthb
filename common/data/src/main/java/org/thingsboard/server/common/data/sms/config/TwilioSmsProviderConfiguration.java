package org.thingsboard.server.common.data.sms.config;

import lombok.Data;

@Data
public class TwilioSmsProviderConfiguration implements SmsProviderConfiguration {

    private String accountSid;
    private String accountToken;
    private String numberFrom;

    @Override
    public SmsProviderType getType() {
        return SmsProviderType.TWILIO;
    }

}
