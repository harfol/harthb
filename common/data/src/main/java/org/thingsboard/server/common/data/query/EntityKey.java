package org.thingsboard.server.common.data.query;

import lombok.Data;

@Data
public class EntityKey {
    private final EntityKeyType type;
    private final String key;
}
