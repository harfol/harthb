package org.thingsboard.server.common.data.oauth2;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.thingsboard.server.common.data.HasName;
import org.thingsboard.server.common.data.SearchTextBasedWithAdditionalInfo;
import org.thingsboard.server.common.data.id.OAuth2ClientRegistrationInfoId;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(exclude = {"clientSecret"})
@NoArgsConstructor
public class OAuth2ClientRegistrationInfo extends SearchTextBasedWithAdditionalInfo<OAuth2ClientRegistrationInfoId> implements HasName {

    private boolean enabled;
    private OAuth2MapperConfig mapperConfig;
    private String clientId;
    private String clientSecret;
    private String authorizationUri;
    private String accessTokenUri;
    private List<String> scope;
    private String userInfoUri;
    private String userNameAttributeName;
    private String jwkSetUri;
    private String clientAuthenticationMethod;
    private String loginButtonLabel;
    private String loginButtonIcon;

    public OAuth2ClientRegistrationInfo(OAuth2ClientRegistrationInfo clientRegistration) {
        super(clientRegistration);
        this.enabled = clientRegistration.enabled;
        this.mapperConfig = clientRegistration.mapperConfig;
        this.clientId = clientRegistration.clientId;
        this.clientSecret = clientRegistration.clientSecret;
        this.authorizationUri = clientRegistration.authorizationUri;
        this.accessTokenUri = clientRegistration.accessTokenUri;
        this.scope = clientRegistration.scope;
        this.userInfoUri = clientRegistration.userInfoUri;
        this.userNameAttributeName = clientRegistration.userNameAttributeName;
        this.jwkSetUri = clientRegistration.jwkSetUri;
        this.clientAuthenticationMethod = clientRegistration.clientAuthenticationMethod;
        this.loginButtonLabel = clientRegistration.loginButtonLabel;
        this.loginButtonIcon = clientRegistration.loginButtonIcon;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getName() {
        return loginButtonLabel;
    }

    @Override
    public String getSearchText() {
        return getName();
    }
}
