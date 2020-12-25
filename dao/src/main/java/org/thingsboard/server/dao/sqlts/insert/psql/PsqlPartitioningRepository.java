package org.thingsboard.server.dao.sqlts.insert.psql;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.thingsboard.server.dao.timeseries.PsqlPartition;
import org.thingsboard.server.dao.util.PsqlDao;
import org.thingsboard.server.dao.util.SqlTsDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SqlTsDao
@PsqlDao
@Repository
@Transactional
public class PsqlPartitioningRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(PsqlPartition partition) {
        entityManager.createNativeQuery(partition.getQuery())
                .executeUpdate();
    }

}
