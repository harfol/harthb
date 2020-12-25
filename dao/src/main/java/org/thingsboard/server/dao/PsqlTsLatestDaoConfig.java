package org.thingsboard.server.dao;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.thingsboard.server.dao.util.PsqlDao;
import org.thingsboard.server.dao.util.SqlTsLatestDao;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"org.thingsboard.server.dao.sqlts.psql"})
@EnableJpaRepositories({"org.thingsboard.server.dao.sqlts.insert.latest.psql", "org.thingsboard.server.dao.sqlts.latest"})
@EntityScan({"org.thingsboard.server.dao.model.sqlts.latest"})
@EnableTransactionManagement
@SqlTsLatestDao
@PsqlDao
public class PsqlTsLatestDaoConfig {

}
