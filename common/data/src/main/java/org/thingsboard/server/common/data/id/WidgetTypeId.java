package org.thingsboard.server.common.data.id;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.thingsboard.server.common.data.EntityType;

public final class WidgetTypeId extends UUIDBased implements EntityId {

    private static final long serialVersionUID = 1L;

    @JsonCreator
    public WidgetTypeId(@JsonProperty("id") UUID id) {
        super(id);
    }

    @JsonIgnore
    @Override
    public EntityType getEntityType() {
        return EntityType.WIDGET_TYPE;
    }
}
