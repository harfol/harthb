package org.thingsboard.rule.engine.profile;

import lombok.Getter;
import org.thingsboard.server.common.data.query.EntityKey;
import org.thingsboard.server.common.data.query.EntityKeyType;

import java.util.Set;

class SnapshotUpdate {

    @Getter
    private final EntityKeyType type;
    @Getter
    private final Set<EntityKey> keys;

    SnapshotUpdate(EntityKeyType type, Set<EntityKey> keys) {
        this.type = type;
        this.keys = keys;
    }

    boolean hasUpdate(){
        return !keys.isEmpty();
    }
}
