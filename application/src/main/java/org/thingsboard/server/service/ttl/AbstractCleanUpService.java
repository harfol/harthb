package org.thingsboard.server.service.ttl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.thingsboard.server.dao.util.PsqlDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;


@Slf4j
public abstract class AbstractCleanUpService {

    @Value("${spring.datasource.url}")
    protected String dbUrl;

    @Value("${spring.datasource.username}")
    protected String dbUserName;

    @Value("${spring.datasource.password}")
    protected String dbPassword;

    protected long executeQuery(Connection conn, String query) throws SQLException {
        try (Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            if (log.isDebugEnabled()) {
                getWarnings(statement);
            }
            resultSet.next();
            return resultSet.getLong(1);
        }
    }

    protected void getWarnings(Statement statement) throws SQLException {
        SQLWarning warnings = statement.getWarnings();
        if (warnings != null) {
            log.debug("{}", warnings.getMessage());
            SQLWarning nextWarning = warnings.getNextWarning();
            while (nextWarning != null) {
                log.debug("{}", nextWarning.getMessage());
                nextWarning = nextWarning.getNextWarning();
            }
        }
    }

    protected abstract void doCleanUp(Connection connection) throws SQLException;

}
