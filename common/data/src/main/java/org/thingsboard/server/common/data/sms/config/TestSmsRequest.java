package org.thingsboard.server.common.data.sms.config;

import lombok.Data;

@Data
public class TestSmsRequest {

    private SmsProviderConfiguration providerConfiguration;
    private String numberTo;
    private String message;

}
