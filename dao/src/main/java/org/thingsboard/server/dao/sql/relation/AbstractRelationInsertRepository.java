package org.thingsboard.server.dao.sql.relation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.thingsboard.server.dao.model.sql.RelationEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Slf4j
public abstract class AbstractRelationInsertRepository implements RelationInsertRepository {

    @PersistenceContext
    protected EntityManager entityManager;

    protected Query getQuery(RelationEntity entity, String query) {
        Query nativeQuery = entityManager.createNativeQuery(query, RelationEntity.class);
        if (entity.getAdditionalInfo() == null) {
            nativeQuery.setParameter("additionalInfo", null);
        } else {
            nativeQuery.setParameter("additionalInfo", entity.getAdditionalInfo().toString());
        }
        return nativeQuery
                .setParameter("fromId", entity.getFromId())
                .setParameter("fromType", entity.getFromType())
                .setParameter("toId", entity.getToId())
                .setParameter("toType", entity.getToType())
                .setParameter("relationTypeGroup", entity.getRelationTypeGroup())
                .setParameter("relationType", entity.getRelationType());
    }

    @Modifying
    protected abstract RelationEntity processSaveOrUpdate(RelationEntity entity);

}