package org.thingsboard.server.dao.cassandra;


import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jmx.JmxReporter;
import com.datastax.oss.driver.api.core.ConsistencyLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.thingsboard.server.dao.cassandra.guava.GuavaSession;
import org.thingsboard.server.dao.cassandra.guava.GuavaSessionBuilder;
import org.thingsboard.server.dao.cassandra.guava.GuavaSessionUtils;

import javax.annotation.PreDestroy;

@Slf4j
public abstract class AbstractCassandraCluster {

    @Value("${cassandra.jmx}")
    private Boolean jmx;
    @Value("${cassandra.metrics}")
    private Boolean metrics;
    @Value("${cassandra.local_datacenter:datacenter1}")
    private String localDatacenter;

    @Autowired
    private CassandraDriverOptions driverOptions;

    @Autowired
    private Environment environment;

    private GuavaSessionBuilder sessionBuilder;

    private GuavaSession session;

    private JmxReporter reporter;

    private String keyspaceName;

    protected void init(String keyspaceName) {
        this.keyspaceName = keyspaceName;
        this.sessionBuilder = GuavaSessionUtils.builder().withConfigLoader(this.driverOptions.getLoader());
        if (!isInstall()) {
            initSession();
        }
    }

    public GuavaSession getSession() {
        if (!isInstall()) {
            return session;
        } else {
            if (session == null) {
                initSession();
            }
            return session;
        }
    }

    public String getKeyspaceName() {
        return keyspaceName;
    }

    private boolean isInstall() {
        return environment.acceptsProfiles("install");
    }

    private void initSession() {
        if (this.keyspaceName != null) {
            this.sessionBuilder.withKeyspace(this.keyspaceName);
        }
        this.sessionBuilder.withLocalDatacenter(localDatacenter);
        session = sessionBuilder.build();
        if (this.metrics && this.jmx) {
            MetricRegistry registry =
                    session.getMetrics().orElseThrow(
                            () -> new IllegalStateException("Metrics are disabled"))
                    .getRegistry();
            this.reporter =
                    JmxReporter.forRegistry(registry)
                            .inDomain("com.datastax.oss.driver")
                            .build();
            this.reporter.start();
        }
    }

    @PreDestroy
    public void close() {
        if (reporter != null) {
            reporter.stop();
        }
        if (session != null) {
            session.close();
        }
    }

    public ConsistencyLevel getDefaultReadConsistencyLevel() {
        return driverOptions.getDefaultReadConsistencyLevel();
    }

    public ConsistencyLevel getDefaultWriteConsistencyLevel() {
        return driverOptions.getDefaultWriteConsistencyLevel();
    }

}
