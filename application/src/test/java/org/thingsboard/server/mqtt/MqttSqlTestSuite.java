package org.thingsboard.server.mqtt;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.extensions.cpsuite.ClasspathSuite;
import org.junit.runner.RunWith;
import org.thingsboard.server.dao.CustomSqlUnit;
import org.thingsboard.server.queue.memory.InMemoryStorage;

import java.util.Arrays;

@RunWith(ClasspathSuite.class)
@ClasspathSuite.ClassnameFilters({
        "org.thingsboard.server.mqtt.rpc.sql.*Test",
        "org.thingsboard.server.mqtt.telemetry.timeseries.sql.*Test",
        "org.thingsboard.server.mqtt.telemetry.attributes.sql.*Test",
        "org.thingsboard.server.mqtt.attributes.updates.sql.*Test",
        "org.thingsboard.server.mqtt.attributes.request.sql.*Test",
        "org.thingsboard.server.mqtt.claim.sql.*Test",
        "org.thingsboard.server.mqtt.provision.sql.*Test"
})
public class MqttSqlTestSuite {

    @ClassRule
    public static CustomSqlUnit sqlUnit = new CustomSqlUnit(
            Arrays.asList("sql/schema-types-hsql.sql", "sql/schema-ts-hsql.sql", "sql/schema-entities-hsql.sql", "sql/system-data.sql"),
            "sql/hsql/drop-all-tables.sql",
            "sql-test.properties");

    @BeforeClass
    public static void cleanupInMemStorage(){
        InMemoryStorage.getInstance().cleanup();
    }
}
