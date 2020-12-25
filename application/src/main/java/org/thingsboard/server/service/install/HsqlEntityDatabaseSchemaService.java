package org.thingsboard.server.service.install;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.thingsboard.server.dao.util.HsqlDao;

@Service
@HsqlDao
@Profile("install")
public class HsqlEntityDatabaseSchemaService extends SqlAbstractDatabaseSchemaService
        implements EntityDatabaseSchemaService {
    protected HsqlEntityDatabaseSchemaService() {
        super("schema-entities-hsql.sql", "schema-entities-idx.sql");
    }
}

