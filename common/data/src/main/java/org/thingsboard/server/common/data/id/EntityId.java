package org.thingsboard.server.common.data.id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.thingsboard.server.common.data.EntityType;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Andrew Shvayka
 */

@JsonDeserialize(using = EntityIdDeserializer.class)
@JsonSerialize(using = EntityIdSerializer.class)
public interface EntityId extends HasUUID, Serializable { //NOSONAR, the constant is closely related to EntityId

    UUID NULL_UUID = UUID.fromString("13814000-1dd2-11b2-8080-808080808080");

    UUID getId();

    EntityType getEntityType();

    @JsonIgnore
    default boolean isNullUid() {
        return NULL_UUID.equals(getId());
    }

}
