package org.thingsboard.server.common.data.id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class OAuth2ClientRegistrationInfoId extends UUIDBased {

    @JsonCreator
    public OAuth2ClientRegistrationInfoId(@JsonProperty("id") UUID id) {
        super(id);
    }

    public static OAuth2ClientRegistrationInfoId fromString(String clientRegistrationInfoId) {
        return new OAuth2ClientRegistrationInfoId(UUID.fromString(clientRegistrationInfoId));
    }
}
