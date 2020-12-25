package org.thingsboard.server.dao.sql.oauth2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.thingsboard.server.common.data.oauth2.OAuth2ClientRegistration;
import org.thingsboard.server.dao.model.sql.OAuth2ClientRegistrationEntity;
import org.thingsboard.server.dao.oauth2.OAuth2ClientRegistrationDao;
import org.thingsboard.server.dao.sql.JpaAbstractDao;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JpaOAuth2ClientRegistrationDao extends JpaAbstractDao<OAuth2ClientRegistrationEntity, OAuth2ClientRegistration> implements OAuth2ClientRegistrationDao {
    private final OAuth2ClientRegistrationRepository repository;

    @Override
    protected Class<OAuth2ClientRegistrationEntity> getEntityClass() {
        return OAuth2ClientRegistrationEntity.class;
    }

    @Override
    protected CrudRepository<OAuth2ClientRegistrationEntity, UUID> getCrudRepository() {
        return repository;
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
