package org.thingsboard.server.common.data.id;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.thingsboard.server.common.data.EntityType;

public class DeviceProfileId extends UUIDBased implements EntityId {

    private static final long serialVersionUID = 1L;

    @JsonCreator
    public DeviceProfileId(@JsonProperty("id") UUID id) {
        super(id);
    }

    public static DeviceProfileId fromString(String deviceProfileId) {
        return new DeviceProfileId(UUID.fromString(deviceProfileId));
    }

    @JsonIgnore
    @Override
    public EntityType getEntityType() {
        return EntityType.DEVICE_PROFILE;
    }
}
