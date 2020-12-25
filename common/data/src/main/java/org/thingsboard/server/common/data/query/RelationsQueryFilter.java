package org.thingsboard.server.common.data.query;

import lombok.Data;
import org.thingsboard.server.common.data.id.EntityId;
import org.thingsboard.server.common.data.relation.EntitySearchDirection;
import org.thingsboard.server.common.data.relation.EntityTypeFilter;
import org.thingsboard.server.common.data.relation.RelationTypeGroup;

import java.util.List;

@Data
public class RelationsQueryFilter implements EntityFilter {

    @Override
    public EntityFilterType getType() {
        return EntityFilterType.RELATIONS_QUERY;
    }

    private EntityId rootEntity;
    private EntitySearchDirection direction;
    private List<EntityTypeFilter> filters;
    private int maxLevel;
    private boolean fetchLastLevelOnly;

}
