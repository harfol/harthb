package org.thingsboard.server.dao.sql.relation;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.thingsboard.server.dao.model.sql.RelationCompositeKey;
import org.thingsboard.server.dao.model.sql.RelationEntity;

import java.util.List;
import java.util.UUID;

public interface RelationRepository
        extends CrudRepository<RelationEntity, RelationCompositeKey>, JpaSpecificationExecutor<RelationEntity> {

    List<RelationEntity> findAllByFromIdAndFromTypeAndRelationTypeGroup(UUID fromId,
                                                                        String fromType,
                                                                        String relationTypeGroup);

    List<RelationEntity> findAllByFromIdAndFromTypeAndRelationTypeAndRelationTypeGroup(UUID fromId,
                                                                                       String fromType,
                                                                                       String relationType,
                                                                                       String relationTypeGroup);

    List<RelationEntity> findAllByToIdAndToTypeAndRelationTypeGroup(UUID toId,
                                                                    String toType,
                                                                    String relationTypeGroup);

    List<RelationEntity> findAllByToIdAndToTypeAndRelationTypeAndRelationTypeGroup(UUID toId,
                                                                                   String toType,
                                                                                   String relationType,
                                                                                   String relationTypeGroup);

    List<RelationEntity> findAllByFromIdAndFromType(UUID fromId,
                                                    String fromType);

    @Transactional
    RelationEntity save(RelationEntity entity);

    @Transactional
    void deleteById(RelationCompositeKey id);

    @Transactional
    void deleteByFromIdAndFromType(UUID fromId, String fromType);
}
