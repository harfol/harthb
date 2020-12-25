package org.thingsboard.server.service.ttl.timeseries;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thingsboard.server.dao.model.ModelConstants;
import org.thingsboard.server.dao.util.PsqlDao;
import org.thingsboard.server.dao.util.SqlTsDao;

import java.sql.Connection;
import java.sql.SQLException;

@SqlTsDao
@PsqlDao
@Service
@Slf4j
public class PsqlTimeseriesCleanUpService extends AbstractTimeseriesCleanUpService {

    @Value("${sql.postgres.ts_key_value_partitioning}")
    private String partitionType;

    @Override
    protected void doCleanUp(Connection connection) throws SQLException {
            long totalPartitionsRemoved = executeQuery(connection, "call drop_partitions_by_max_ttl('" + partitionType + "'," + systemTtl + ", 0);");
            log.info("Total partitions removed by TTL: [{}]", totalPartitionsRemoved);
            long totalEntitiesTelemetryRemoved = executeQuery(connection, "call cleanup_timeseries_by_ttl('" + ModelConstants.NULL_UUID + "'," + systemTtl + ", 0);");
            log.info("Total telemetry removed stats by TTL for entities: [{}]", totalEntitiesTelemetryRemoved);
    }
}