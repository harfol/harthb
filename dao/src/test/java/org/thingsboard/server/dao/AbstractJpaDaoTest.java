package org.thingsboard.server.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

/**
 * Created by Valerii Sosliuk on 4/22/2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {JpaDaoConfig.class, HsqlTsDaoConfig.class, JpaDbunitTestConfig.class})
@TestPropertySource("classpath:sql-test.properties")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DbUnitConfiguration(databaseConnection = "dbUnitDatabaseConnection")
public abstract class AbstractJpaDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

}
