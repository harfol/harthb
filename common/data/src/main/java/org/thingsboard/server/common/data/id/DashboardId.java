package org.thingsboard.server.common.data.id;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.thingsboard.server.common.data.EntityType;

public class DashboardId extends UUIDBased implements EntityId {

    @JsonCreator
    public DashboardId(@JsonProperty("id") UUID id) {
        super(id);
    }

    public static DashboardId fromString(String dashboardId) {
        return new DashboardId(UUID.fromString(dashboardId));
    }

    @JsonIgnore
    @Override
    public EntityType getEntityType() {
        return EntityType.DASHBOARD;
    }
}
