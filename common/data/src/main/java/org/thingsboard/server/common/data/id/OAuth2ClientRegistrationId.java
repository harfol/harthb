package org.thingsboard.server.common.data.id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class OAuth2ClientRegistrationId extends UUIDBased {

    @JsonCreator
    public OAuth2ClientRegistrationId(@JsonProperty("id") UUID id) {
        super(id);
    }

    public static OAuth2ClientRegistrationId fromString(String clientRegistrationId) {
        return new OAuth2ClientRegistrationId(UUID.fromString(clientRegistrationId));
    }
}
