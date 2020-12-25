package org.thingsboard.rule.engine.data;

import lombok.Data;
import org.thingsboard.server.common.data.relation.EntitySearchDirection;
import org.thingsboard.server.common.data.relation.EntityTypeFilter;

import java.util.List;

@Data
public class RelationsQuery {

    private EntitySearchDirection direction;
    private int maxLevel = 1;
    private List<EntityTypeFilter> filters;
    private boolean fetchLastLevelOnly = false;
}
