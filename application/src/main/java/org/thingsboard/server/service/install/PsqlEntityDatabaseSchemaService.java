package org.thingsboard.server.service.install;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.thingsboard.server.dao.util.PsqlDao;

@Service
@PsqlDao
@Profile("install")
public class PsqlEntityDatabaseSchemaService extends SqlAbstractDatabaseSchemaService
        implements EntityDatabaseSchemaService {
    public PsqlEntityDatabaseSchemaService() {
        super("schema-entities.sql", "schema-entities-idx.sql");
    }
}
