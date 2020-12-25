package org.thingsboard.server.dao.oauth2;

import org.thingsboard.server.common.data.oauth2.OAuth2ClientInfo;
import org.thingsboard.server.common.data.oauth2.OAuth2ClientRegistrationInfo;
import org.thingsboard.server.common.data.oauth2.OAuth2ClientsParams;

import java.util.List;
import java.util.UUID;

public interface OAuth2Service {
    List<OAuth2ClientInfo> getOAuth2Clients(String domainScheme, String domainName);

    void saveOAuth2Params(OAuth2ClientsParams oauth2Params);

    OAuth2ClientsParams findOAuth2Params();

    OAuth2ClientRegistrationInfo findClientRegistrationInfo(UUID id);

    List<OAuth2ClientRegistrationInfo> findAllClientRegistrationInfos();
}
