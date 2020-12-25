package org.thingsboard.server.common.data.oauth2;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;
import org.thingsboard.server.common.data.id.OAuth2ClientRegistrationInfoId;

import java.util.List;

@EqualsAndHashCode
@Data
@ToString(exclude = {"clientSecret"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientRegistrationDto {
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
    private JsonNode additionalInfo;
}
