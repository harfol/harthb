package org.thingsboard.server.dao.cassandra.guava;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import com.datastax.oss.driver.api.core.context.DriverContext;
import com.datastax.oss.driver.api.core.metadata.Node;
import com.datastax.oss.driver.api.core.metadata.NodeStateListener;
import com.datastax.oss.driver.api.core.metadata.schema.SchemaChangeListener;
import com.datastax.oss.driver.api.core.session.SessionBuilder;
import com.datastax.oss.driver.api.core.tracker.RequestTracker;
import com.datastax.oss.driver.api.core.type.codec.TypeCodec;
import edu.umd.cs.findbugs.annotations.NonNull;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class GuavaSessionBuilder extends SessionBuilder<GuavaSessionBuilder, GuavaSession> {

    @Override
    protected DriverContext buildContext(
            DriverConfigLoader configLoader,
            List<TypeCodec<?>> typeCodecs,
            NodeStateListener nodeStateListener,
            SchemaChangeListener schemaChangeListener,
            RequestTracker requestTracker,
            Map<String, String> localDatacenters,
            Map<String, Predicate<Node>> nodeFilters,
            ClassLoader classLoader) {
        return new GuavaDriverContext(
                configLoader,
                typeCodecs,
                nodeStateListener,
                schemaChangeListener,
                requestTracker,
                localDatacenters,
                nodeFilters,
                classLoader);
    }

    @Override
    protected GuavaSession wrap(@NonNull CqlSession defaultSession) {
        return new DefaultGuavaSession(defaultSession);
    }
}
