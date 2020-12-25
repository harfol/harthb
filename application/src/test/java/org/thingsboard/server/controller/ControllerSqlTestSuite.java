package org.thingsboard.server.controller;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.extensions.cpsuite.ClasspathSuite;
import org.junit.runner.RunWith;
import org.thingsboard.server.dao.CustomSqlUnit;
import org.thingsboard.server.queue.memory.InMemoryStorage;

import java.util.Arrays;

@RunWith(ClasspathSuite.class)
@ClasspathSuite.ClassnameFilters({
//        "org.thingsboard.server.controller.sql.WebsocketApiSqlTest",
//        "org.thingsboard.server.controller.sql.TenantProfileControllerSqlTest",
        "org.thingsboard.server.controller.sql.*Test",
        })
public class ControllerSqlTestSuite {

    @ClassRule
    public static CustomSqlUnit sqlUnit = new CustomSqlUnit(
            Arrays.asList("sql/schema-types-hsql.sql", "sql/schema-ts-hsql.sql", "sql/schema-entities-hsql.sql", "sql/schema-entities-idx.sql", "sql/system-data.sql"),
            "sql/hsql/drop-all-tables.sql",
            "sql-test.properties");

    @BeforeClass
    public static void cleanupInMemStorage(){
        InMemoryStorage.getInstance().cleanup();
    }
}
