package org.thingsboard.server.dao.model.sql;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.TypeDef;
import org.thingsboard.server.common.data.oauth2.OAuth2ClientRegistrationInfo;
import org.thingsboard.server.dao.model.ModelConstants;
import org.thingsboard.server.dao.util.mapping.JsonStringType;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@TypeDef(name = "json", typeClass = JsonStringType.class)
@Table(name = ModelConstants.OAUTH2_CLIENT_REGISTRATION_INFO_COLUMN_FAMILY_NAME)
public class OAuth2ClientRegistrationInfoEntity extends AbstractOAuth2ClientRegistrationInfoEntity<OAuth2ClientRegistrationInfo> {

    public OAuth2ClientRegistrationInfoEntity() {
        super();
    }

    public OAuth2ClientRegistrationInfoEntity(OAuth2ClientRegistrationInfo clientRegistration) {
        super(clientRegistration);
    }

    public OAuth2ClientRegistrationInfoEntity(OAuth2ClientRegistrationInfoEntity oAuth2ClientRegistrationInfoEntity) {
        super(oAuth2ClientRegistrationInfoEntity);
    }

    @Override
    public OAuth2ClientRegistrationInfo toData() {
        return super.toOAuth2ClientRegistrationInfo();
    }
}
