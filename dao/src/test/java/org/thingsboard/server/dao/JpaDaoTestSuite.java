package org.thingsboard.server.dao;

import org.junit.ClassRule;
import org.junit.extensions.cpsuite.ClasspathSuite;
import org.junit.extensions.cpsuite.ClasspathSuite.ClassnameFilters;
import org.junit.runner.RunWith;

import java.util.Arrays;

@RunWith(ClasspathSuite.class)
@ClassnameFilters({
        "org.thingsboard.server.dao.sql.*THIS_MUST_BE_FIXED_Test"
})
public class JpaDaoTestSuite {

    @ClassRule
    public static CustomSqlUnit sqlUnit = new CustomSqlUnit(
            Arrays.asList("sql/schema-ts-hsql.sql", "sql/schema-entities-hsql.sql", "sql/system-data.sql"),
            "sql/hsql/drop-all-tables.sql",
            "sql-test.properties"
    );

//    @ClassRule
//    public static CustomSqlUnit sqlUnit = new CustomSqlUnit(
//            Arrays.asList("sql/schema-ts-psql.sql", "sql/schema-entities.sql", "sql/system-data.sql"),
//            "sql/psql/drop-all-tables.sql",
//            "sql-test.properties"
//    );

//    @ClassRule
//    public static CustomSqlUnit sqlUnit = new CustomSqlUnit(
//            Arrays.asList("sql/schema-timescale.sql", "sql/schema-timescale-idx.sql", "sql/schema-entities.sql", "sql/system-data.sql"),
//            "sql/timescale/drop-all-tables.sql",
//            "sql-test.properties"
//    );

}
