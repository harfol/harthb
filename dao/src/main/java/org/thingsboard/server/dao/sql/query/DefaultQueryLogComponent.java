package org.thingsboard.server.dao.sql.query;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class DefaultQueryLogComponent implements QueryLogComponent {

    @Value("${sql.log_queries:false}")
    private boolean logSqlQueries;
    @Value("${sql.log_queries_threshold:5000}")
    private long logQueriesThreshold;

    @Override
    public void logQuery(QueryContext ctx, String query, long duration) {
        if (logSqlQueries && duration > logQueriesThreshold) {
            log.info("QUERY: {} took {}ms", query, duration);
            Arrays.asList(ctx.getParameterNames()).forEach(param -> log.info("QUERY PARAM: {} -> {}", param, ctx.getValue(param)));
        }
    }
}
