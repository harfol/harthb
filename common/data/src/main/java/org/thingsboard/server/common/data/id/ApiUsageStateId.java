package org.thingsboard.server.common.data.id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.thingsboard.server.common.data.EntityType;

import java.util.UUID;

public class ApiUsageStateId extends UUIDBased implements EntityId {

    @JsonCreator
    public ApiUsageStateId(@JsonProperty("id") UUID id) {
        super(id);
    }

    public static ApiUsageStateId fromString(String userId) {
        return new ApiUsageStateId(UUID.fromString(userId));
    }

    @JsonIgnore
    @Override
    public EntityType getEntityType() {
        return EntityType.API_USAGE_STATE;
    }

}
