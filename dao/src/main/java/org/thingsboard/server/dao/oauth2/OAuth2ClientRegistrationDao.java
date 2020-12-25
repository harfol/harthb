package org.thingsboard.server.dao.oauth2;

import org.thingsboard.server.common.data.oauth2.OAuth2ClientRegistration;
import org.thingsboard.server.dao.Dao;

public interface OAuth2ClientRegistrationDao extends Dao<OAuth2ClientRegistration> {
    void deleteAll();
}
