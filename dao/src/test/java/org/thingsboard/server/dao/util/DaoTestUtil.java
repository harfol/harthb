package org.thingsboard.server.dao.util;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.DriverManager;

public class DaoTestUtil {
    private static final String POSTGRES_DRIVER_CLASS = "org.postgresql.Driver";
    private static final String H2_DRIVER_CLASS = "org.hsqldb.jdbc.JDBCDriver";


    public static SqlDbType getSqlDbType(JdbcTemplate template){
        try {
            String driverName = DriverManager.getDriver(template.getDataSource().getConnection().getMetaData().getURL()).getClass().getName();
            if (POSTGRES_DRIVER_CLASS.equals(driverName)) {
                return SqlDbType.POSTGRES;
            } else if (H2_DRIVER_CLASS.equals(driverName)) {
                return SqlDbType.H2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
