package org.thingsboard.server.common.data.oauth2;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.thingsboard.server.common.data.BaseData;
import org.thingsboard.server.common.data.id.OAuth2ClientRegistrationId;
import org.thingsboard.server.common.data.id.OAuth2ClientRegistrationInfoId;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@NoArgsConstructor
public class OAuth2ClientRegistration extends BaseData<OAuth2ClientRegistrationId> {

    private OAuth2ClientRegistrationInfoId clientRegistrationId;
    private String domainName;
    private SchemeType domainScheme;

    public OAuth2ClientRegistration(OAuth2ClientRegistration clientRegistration) {
        super(clientRegistration);
        this.clientRegistrationId = clientRegistration.clientRegistrationId;
        this.domainName = clientRegistration.domainName;
        this.domainScheme = clientRegistration.domainScheme;
    }
}
