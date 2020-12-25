package org.thingsboard.server.common.data.query;

import lombok.Getter;

public class EntityCountQuery {

    @Getter
    private EntityFilter entityFilter;

    public EntityCountQuery() {}

    public EntityCountQuery(EntityFilter entityFilter) {
        this.entityFilter = entityFilter;
    }
}
