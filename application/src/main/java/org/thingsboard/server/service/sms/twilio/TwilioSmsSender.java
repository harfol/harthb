package org.thingsboard.server.service.sms.twilio;

import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.apache.commons.lang3.StringUtils;
import org.thingsboard.server.common.data.sms.config.TwilioSmsProviderConfiguration;
import org.thingsboard.rule.engine.api.sms.exception.SmsException;
import org.thingsboard.rule.engine.api.sms.exception.SmsSendException;
import org.thingsboard.server.service.sms.AbstractSmsSender;

public class TwilioSmsSender extends AbstractSmsSender {

    private TwilioRestClient twilioRestClient;
    private String numberFrom;

    public TwilioSmsSender(TwilioSmsProviderConfiguration config) {
        if (StringUtils.isEmpty(config.getAccountSid()) || StringUtils.isEmpty(config.getAccountToken()) || StringUtils.isEmpty(config.getNumberFrom())) {
            throw new IllegalArgumentException("Invalid twilio sms provider configuration: accountSid, accountToken and numberFrom should be specified!");
        }
        this.numberFrom = this.validatePhoneNumber(config.getNumberFrom());
        this.twilioRestClient = new TwilioRestClient.Builder(config.getAccountSid(), config.getAccountToken()).build();
    }

    @Override
    public int sendSms(String numberTo, String message) throws SmsException {
        numberTo = this.validatePhoneNumber(numberTo);
        message = this.prepareMessage(message);
        try {
            String numSegments = Message.creator(new PhoneNumber(numberTo), new PhoneNumber(this.numberFrom), message).create(this.twilioRestClient).getNumSegments();
            return Integer.valueOf(numSegments);
        } catch (Exception e) {
            throw new SmsSendException("Failed to send SMS message - " + e.getMessage(), e);
        }
    }

    @Override
    public void destroy() {

    }
}
