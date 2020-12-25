package org.thingsboard.server.common.data.oauth2;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExtendedOAuth2ClientRegistrationInfo extends OAuth2ClientRegistrationInfo {

    private String domainName;
    private SchemeType domainScheme;

    public ExtendedOAuth2ClientRegistrationInfo() {
        super();
    }

    public ExtendedOAuth2ClientRegistrationInfo(OAuth2ClientRegistrationInfo oAuth2ClientRegistrationInfo,
                                                SchemeType domainScheme,
                                                String domainName) {
        super(oAuth2ClientRegistrationInfo);
        this.domainScheme = domainScheme;
        this.domainName = domainName;
    }
}
