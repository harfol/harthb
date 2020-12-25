package org.thingsboard.server.service.security.auth.oauth2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.thingsboard.server.common.data.oauth2.OAuth2ClientRegistrationInfo;
import org.thingsboard.server.common.data.oauth2.OAuth2MapperConfig;
import org.thingsboard.server.dao.oauth2.OAuth2User;
import org.thingsboard.server.service.security.model.SecurityUser;

import java.util.Map;

@Service(value = "basicOAuth2ClientMapper")
@Slf4j
public class BasicOAuth2ClientMapper extends AbstractOAuth2ClientMapper implements OAuth2ClientMapper {

    @Override
    public SecurityUser getOrCreateUserByClientPrincipal(OAuth2AuthenticationToken token, String providerAccessToken, OAuth2ClientRegistrationInfo clientRegistration) {
        OAuth2MapperConfig config = clientRegistration.getMapperConfig();
        Map<String, Object> attributes = token.getPrincipal().getAttributes();
        String email = BasicMapperUtils.getStringAttributeByKey(attributes, config.getBasic().getEmailAttributeKey());
        OAuth2User oauth2User = BasicMapperUtils.getOAuth2User(email, attributes, config);

        return getOrCreateSecurityUserFromOAuth2User(oauth2User, clientRegistration);
    }
}
