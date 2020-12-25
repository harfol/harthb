package org.thingsboard.server.dao;

import org.cassandraunit.dataset.cql.ClassPathCQLDataSet;
import org.junit.ClassRule;
import org.junit.extensions.cpsuite.ClasspathSuite;
import org.junit.extensions.cpsuite.ClasspathSuite.ClassnameFilters;
import org.junit.runner.RunWith;

import java.util.Arrays;

@RunWith(ClasspathSuite.class)
@ClassnameFilters({
        "org.thingsboard.server.dao.service.*ServiceNoSqlTest"
})
public class NoSqlDaoServiceTestSuite {

    @ClassRule
    public static CustomSqlUnit sqlUnit = new CustomSqlUnit(
            Arrays.asList("sql/schema-types-hsql.sql", "sql/schema-entities-hsql.sql", "sql/schema-entities-idx.sql", "sql/system-data.sql", "sql/system-test.sql"),
            "sql/hsql/drop-all-tables.sql",
            "nosql-test.properties"
    );

    @ClassRule
    public static CustomCassandraCQLUnit cassandraUnit =
            new CustomCassandraCQLUnit(
                    Arrays.asList(
                            new ClassPathCQLDataSet("cassandra/schema-ts.cql", false, false),
                            new ClassPathCQLDataSet("cassandra/schema-ts-latest.cql", false, false)
                    ),
                    "cassandra-test.yaml", 30000L);

}
