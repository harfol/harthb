package org.thingsboard.server.common.data.sms.config;

import lombok.Data;

@Data
public class AwsSnsSmsProviderConfiguration implements SmsProviderConfiguration {

    private String accessKeyId;
    private String secretAccessKey;
    private String region;

    @Override
    public SmsProviderType getType() {
        return SmsProviderType.AWS_SNS;
    }

}
