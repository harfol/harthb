package org.thingsboard.server.common.data.id;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.thingsboard.server.common.data.EntityType;

public final class TenantId extends UUIDBased implements EntityId {

    @JsonIgnore
    public static final TenantId SYS_TENANT_ID = new TenantId(EntityId.NULL_UUID);

    private static final long serialVersionUID = 1L;

    @JsonCreator
    public TenantId(@JsonProperty("id") UUID id) {
        super(id);
    }

    @JsonIgnore
    @Override
    public EntityType getEntityType() {
        return EntityType.TENANT;
    }
}
