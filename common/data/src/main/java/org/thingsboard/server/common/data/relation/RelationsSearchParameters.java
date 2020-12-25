package org.thingsboard.server.common.data.relation;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.thingsboard.server.common.data.EntityType;
import org.thingsboard.server.common.data.id.EntityId;
import org.thingsboard.server.common.data.id.EntityIdFactory;

import java.util.UUID;

/**
 * Created by ashvayka on 03.05.17.
 */
@Data
@AllArgsConstructor
public class RelationsSearchParameters {

    private UUID rootId;
    private EntityType rootType;
    private EntitySearchDirection direction;
    private RelationTypeGroup relationTypeGroup;
    private int maxLevel = 1;
    private boolean fetchLastLevelOnly;

    public RelationsSearchParameters(EntityId entityId, EntitySearchDirection direction, int maxLevel, boolean fetchLastLevelOnly) {
        this(entityId, direction, maxLevel, RelationTypeGroup.COMMON, fetchLastLevelOnly);
    }

    public RelationsSearchParameters(EntityId entityId, EntitySearchDirection direction, int maxLevel, RelationTypeGroup relationTypeGroup, boolean fetchLastLevelOnly) {
        this.rootId = entityId.getId();
        this.rootType = entityId.getEntityType();
        this.direction = direction;
        this.maxLevel = maxLevel;
        this.relationTypeGroup = relationTypeGroup;
        this.fetchLastLevelOnly = fetchLastLevelOnly;
    }

    public EntityId getEntityId() {
        return EntityIdFactory.getByTypeAndUuid(rootType, rootId);
    }
}
