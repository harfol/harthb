package org.thingsboard.server.dao.sql.relation;

import org.thingsboard.server.dao.model.sql.RelationEntity;

public interface RelationInsertRepository {

    RelationEntity saveOrUpdate(RelationEntity entity);

}