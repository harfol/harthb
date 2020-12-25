package org.thingsboard.server.dao.sql.oauth2;

import org.springframework.data.repository.CrudRepository;
import org.thingsboard.server.dao.model.sql.OAuth2ClientRegistrationTemplateEntity;

import java.util.UUID;

public interface OAuth2ClientRegistrationTemplateRepository extends CrudRepository<OAuth2ClientRegistrationTemplateEntity, UUID> {

    OAuth2ClientRegistrationTemplateEntity findByProviderId(String providerId);

}
