package org.thingsboard.server.dao.sql.oauth2;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.thingsboard.server.common.data.oauth2.SchemeType;
import org.thingsboard.server.dao.model.sql.ExtendedOAuth2ClientRegistrationInfoEntity;
import org.thingsboard.server.dao.model.sql.OAuth2ClientRegistrationInfoEntity;

import java.util.List;
import java.util.UUID;

public interface OAuth2ClientRegistrationInfoRepository extends CrudRepository<OAuth2ClientRegistrationInfoEntity, UUID> {
    @Query("SELECT new OAuth2ClientRegistrationInfoEntity(cr_info) " +
            "FROM OAuth2ClientRegistrationInfoEntity cr_info " +
            "LEFT JOIN OAuth2ClientRegistrationEntity cr on cr_info.id = cr.clientRegistrationInfoId " +
            "WHERE cr.domainName = :domainName " +
            "AND cr.domainScheme IN (:domainSchemes)")
    List<OAuth2ClientRegistrationInfoEntity> findAllByDomainSchemesAndName(@Param("domainSchemes") List<SchemeType> domainSchemes,
                                                                           @Param("domainName") String domainName);

    @Query("SELECT new org.thingsboard.server.dao.model.sql.ExtendedOAuth2ClientRegistrationInfoEntity(cr_info, cr.domainName, cr.domainScheme) " +
            "FROM OAuth2ClientRegistrationInfoEntity cr_info " +
            "LEFT JOIN OAuth2ClientRegistrationEntity cr on cr_info.id = cr.clientRegistrationInfoId ")
    List<ExtendedOAuth2ClientRegistrationInfoEntity> findAllExtended();
}
