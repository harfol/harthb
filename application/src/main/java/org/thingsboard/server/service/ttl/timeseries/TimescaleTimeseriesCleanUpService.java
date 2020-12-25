package org.thingsboard.server.service.ttl.timeseries;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thingsboard.server.dao.model.ModelConstants;
import org.thingsboard.server.dao.util.TimescaleDBTsDao;

import java.sql.Connection;
import java.sql.SQLException;

@TimescaleDBTsDao
@Service
@Slf4j
public class TimescaleTimeseriesCleanUpService extends AbstractTimeseriesCleanUpService {

    @Override
    protected void doCleanUp(Connection connection) throws SQLException {
        long totalEntitiesTelemetryRemoved = executeQuery(connection, "call cleanup_timeseries_by_ttl('" + ModelConstants.NULL_UUID + "'," + systemTtl + ", 0);");
        log.info("Total telemetry removed stats by TTL for entities: [{}]", totalEntitiesTelemetryRemoved);
    }
}
