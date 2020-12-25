package org.thingsboard.server.common.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.thingsboard.server.common.data.id.EntityId;
import org.thingsboard.server.common.data.id.EntityIdFactory;

import java.util.UUID;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeviceProfileInfo extends EntityInfo {

    private final DeviceProfileType type;
    private final DeviceTransportType transportType;

    @JsonCreator
    public DeviceProfileInfo(@JsonProperty("id") EntityId id,
                             @JsonProperty("name") String name,
                             @JsonProperty("type") DeviceProfileType type,
                             @JsonProperty("transportType") DeviceTransportType transportType) {
        super(id, name);
        this.type = type;
        this.transportType = transportType;
    }

    public DeviceProfileInfo(UUID uuid, String name, DeviceProfileType type, DeviceTransportType transportType) {
        super(EntityIdFactory.getByTypeAndUuid(EntityType.DEVICE_PROFILE, uuid), name);
        this.type = type;
        this.transportType = transportType;
    }

}
