package org.thingsboard.server.service.install;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.thingsboard.server.dao.util.HsqlDao;
import org.thingsboard.server.dao.util.SqlTsDao;

@Service
@SqlTsDao
@HsqlDao
@Profile("install")
public class HsqlTsDatabaseSchemaService extends SqlAbstractDatabaseSchemaService
        implements TsDatabaseSchemaService {
    public HsqlTsDatabaseSchemaService() {
        super("schema-ts-hsql.sql", null);
    }
}