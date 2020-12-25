package org.thingsboard.server.dao.model.sql;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thingsboard.server.common.data.oauth2.ExtendedOAuth2ClientRegistrationInfo;
import org.thingsboard.server.common.data.oauth2.SchemeType;

@Data
@EqualsAndHashCode(callSuper = true)
public class ExtendedOAuth2ClientRegistrationInfoEntity extends AbstractOAuth2ClientRegistrationInfoEntity<ExtendedOAuth2ClientRegistrationInfo> {

    private String domainName;
    private SchemeType domainScheme;

    public ExtendedOAuth2ClientRegistrationInfoEntity() {
        super();
    }

    public ExtendedOAuth2ClientRegistrationInfoEntity(OAuth2ClientRegistrationInfoEntity oAuth2ClientRegistrationInfoEntity,
                                                      String domainName,
                                                      SchemeType domainScheme) {
        super(oAuth2ClientRegistrationInfoEntity);
        this.domainName = domainName;
        this.domainScheme = domainScheme;
    }

    @Override
    public ExtendedOAuth2ClientRegistrationInfo toData() {
        return new ExtendedOAuth2ClientRegistrationInfo(super.toOAuth2ClientRegistrationInfo(),
                domainScheme,
                domainName);
    }
}
