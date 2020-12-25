package org.thingsboard.server.common.data.id;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.thingsboard.server.common.data.EntityType;

public class TenantProfileId extends UUIDBased implements EntityId {

    private static final long serialVersionUID = 1L;

    @JsonCreator
    public TenantProfileId(@JsonProperty("id") UUID id) {
        super(id);
    }

    public static TenantProfileId fromString(String tenantProfileId) {
        return new TenantProfileId(UUID.fromString(tenantProfileId));
    }

    @JsonIgnore
    @Override
    public EntityType getEntityType() {
        return EntityType.TENANT_PROFILE;
    }
}
