package org.thingsboard.server.dao.sql.oauth2;

import org.springframework.data.repository.CrudRepository;
import org.thingsboard.server.dao.model.sql.OAuth2ClientRegistrationEntity;

import java.util.UUID;

public interface OAuth2ClientRegistrationRepository extends CrudRepository<OAuth2ClientRegistrationEntity, UUID> {
}
