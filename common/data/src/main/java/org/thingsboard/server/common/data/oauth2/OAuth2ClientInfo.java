package org.thingsboard.server.common.data.oauth2;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2ClientInfo {

    private String name;
    private String icon;
    private String url;

    public OAuth2ClientInfo(OAuth2ClientInfo oauth2ClientInfo) {
        this.name = oauth2ClientInfo.getName();
        this.icon = oauth2ClientInfo.getIcon();
        this.url = oauth2ClientInfo.getUrl();
    }
}
