package org.thingsboard.server.install;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.thingsboard.server.dao.audit.AuditLogLevelFilter;

import java.util.HashMap;

@Configuration
@Profile("install")
public class ThingsboardInstallConfiguration {

    @Bean
    public AuditLogLevelFilter emptyAuditLogLevelFilter() {
        return new AuditLogLevelFilter(new HashMap<>());
    }
}
