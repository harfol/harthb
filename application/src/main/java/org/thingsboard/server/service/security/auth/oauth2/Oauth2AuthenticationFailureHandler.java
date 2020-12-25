package org.thingsboard.server.service.security.auth.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.thingsboard.server.common.data.id.CustomerId;
import org.thingsboard.server.common.data.id.EntityId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.service.security.system.SystemSecurityService;
import org.thingsboard.server.utils.MiscUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component(value = "oauth2AuthenticationFailureHandler")
@ConditionalOnProperty(prefix = "security.oauth2", value = "enabled", havingValue = "true")
public class Oauth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final SystemSecurityService systemSecurityService;

    @Autowired
    public Oauth2AuthenticationFailureHandler(final SystemSecurityService systemSecurityService) {
        this.systemSecurityService = systemSecurityService;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        String baseUrl = this.systemSecurityService.getBaseUrl(TenantId.SYS_TENANT_ID, new CustomerId(EntityId.NULL_UUID), request);
        getRedirectStrategy().sendRedirect(request, response, baseUrl + "/login?loginError=" +
                URLEncoder.encode(exception.getMessage(), StandardCharsets.UTF_8.toString()));
    }
}
