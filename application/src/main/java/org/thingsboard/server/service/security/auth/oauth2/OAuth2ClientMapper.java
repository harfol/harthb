package org.thingsboard.server.service.security.auth.oauth2;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.thingsboard.server.common.data.oauth2.OAuth2ClientRegistrationInfo;
import org.thingsboard.server.service.security.model.SecurityUser;

public interface OAuth2ClientMapper {
    SecurityUser getOrCreateUserByClientPrincipal(OAuth2AuthenticationToken token, String providerAccessToken, OAuth2ClientRegistrationInfo clientRegistration);
}
