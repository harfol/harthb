package org.thingsboard.server.dao.util;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@ConditionalOnProperty(prefix = "spring.jpa", value = "database-platform", havingValue = "org.hibernate.dialect.PostgreSQLDialect")
public @interface PsqlDao {
}