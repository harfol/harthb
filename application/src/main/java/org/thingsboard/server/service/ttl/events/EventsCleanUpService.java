package org.thingsboard.server.service.ttl.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thingsboard.server.dao.util.PsqlDao;
import org.thingsboard.server.service.ttl.AbstractCleanUpService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@PsqlDao
@Slf4j
@Service
public class EventsCleanUpService extends AbstractCleanUpService {

    @Value("${sql.ttl.events.events_ttl}")
    private long ttl;

    @Value("${sql.ttl.events.debug_events_ttl}")
    private long debugTtl;

    @Value("${sql.ttl.events.enabled}")
    private boolean ttlTaskExecutionEnabled;

    @Scheduled(initialDelayString = "${sql.ttl.events.execution_interval_ms}", fixedDelayString = "${sql.ttl.events.execution_interval_ms}")
    public void cleanUp() {
        if (ttlTaskExecutionEnabled) {
            try (Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword)) {
                doCleanUp(conn);
            } catch (SQLException e) {
                log.error("SQLException occurred during TTL task execution ", e);
            }
        }
    }

    @Override
    protected void doCleanUp(Connection connection) throws SQLException {
        long totalEventsRemoved = executeQuery(connection, "call cleanup_events_by_ttl(" + ttl + ", " + debugTtl + ", 0);");
        log.info("Total events removed by TTL: [{}]", totalEventsRemoved);
    }
}