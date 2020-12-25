package org.thingsboard.server.dao.sql.oauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.thingsboard.server.common.data.oauth2.ExtendedOAuth2ClientRegistrationInfo;
import org.thingsboard.server.common.data.oauth2.OAuth2ClientRegistrationInfo;
import org.thingsboard.server.common.data.oauth2.SchemeType;
import org.thingsboard.server.dao.DaoUtil;
import org.thingsboard.server.dao.model.sql.OAuth2ClientRegistrationInfoEntity;
import org.thingsboard.server.dao.oauth2.OAuth2ClientRegistrationInfoDao;
import org.thingsboard.server.dao.sql.JpaAbstractDao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JpaOAuth2ClientRegistrationInfoDao extends JpaAbstractDao<OAuth2ClientRegistrationInfoEntity, OAuth2ClientRegistrationInfo> implements OAuth2ClientRegistrationInfoDao {
    private final OAuth2ClientRegistrationInfoRepository repository;

    @Override
    protected Class<OAuth2ClientRegistrationInfoEntity> getEntityClass() {
        return OAuth2ClientRegistrationInfoEntity.class;
    }

    @Override
    protected CrudRepository<OAuth2ClientRegistrationInfoEntity, UUID> getCrudRepository() {
        return repository;
    }

    @Override
    public List<OAuth2ClientRegistrationInfo> findAll() {
        Iterable<OAuth2ClientRegistrationInfoEntity> entities = repository.findAll();
        List<OAuth2ClientRegistrationInfo> result = new ArrayList<>();
        entities.forEach(entity -> {
            result.add(DaoUtil.getData(entity));
        });
        return result;
    }

    @Override
    public List<ExtendedOAuth2ClientRegistrationInfo> findAllExtended() {
        return repository.findAllExtended().stream()
                .map(DaoUtil::getData)
                .collect(Collectors.toList());
    }

    @Override
    public List<OAuth2ClientRegistrationInfo> findByDomainSchemesAndDomainName(List<SchemeType> domainSchemes, String domainName) {
        List<OAuth2ClientRegistrationInfoEntity> entities = repository.findAllByDomainSchemesAndName(domainSchemes, domainName);
        return entities.stream().map(DaoUtil::getData).collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
