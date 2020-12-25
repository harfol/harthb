package org.thingsboard.server.dao.oauth2;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "security.oauth2")
@Data
public class OAuth2Configuration {
    private String loginProcessingUrl;
    private Map<String, String> githubMapper;
}
