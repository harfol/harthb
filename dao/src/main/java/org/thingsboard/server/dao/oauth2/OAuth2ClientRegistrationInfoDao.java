package org.thingsboard.server.dao.oauth2;

import org.thingsboard.server.common.data.oauth2.ExtendedOAuth2ClientRegistrationInfo;
import org.thingsboard.server.common.data.oauth2.OAuth2ClientRegistrationInfo;
import org.thingsboard.server.common.data.oauth2.SchemeType;
import org.thingsboard.server.dao.Dao;

import java.util.List;
import java.util.Set;

public interface OAuth2ClientRegistrationInfoDao extends Dao<OAuth2ClientRegistrationInfo> {
    List<OAuth2ClientRegistrationInfo> findAll();

    List<ExtendedOAuth2ClientRegistrationInfo> findAllExtended();

    List<OAuth2ClientRegistrationInfo> findByDomainSchemesAndDomainName(List<SchemeType> domainSchemes, String domainName);

    void deleteAll();
}
