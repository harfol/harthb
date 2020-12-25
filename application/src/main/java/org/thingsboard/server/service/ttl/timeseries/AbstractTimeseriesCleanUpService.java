package org.thingsboard.server.service.ttl.timeseries;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.thingsboard.server.service.ttl.AbstractCleanUpService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public abstract class AbstractTimeseriesCleanUpService extends AbstractCleanUpService {

    @Value("${sql.ttl.ts.ts_key_value_ttl}")
    protected long systemTtl;

    @Value("${sql.ttl.ts.enabled}")
    private boolean ttlTaskExecutionEnabled;

    @Scheduled(initialDelayString = "${sql.ttl.ts.execution_interval_ms}", fixedDelayString = "${sql.ttl.ts.execution_interval_ms}")
    public void cleanUp() {
        if (ttlTaskExecutionEnabled) {
            try (Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword)) {
                doCleanUp(conn);
            } catch (SQLException e) {
                log.error("SQLException occurred during TTL task execution ", e);
            }
        }
    }

}