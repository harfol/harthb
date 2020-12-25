package org.thingsboard.server.common.data.id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.thingsboard.server.common.data.EntityType;

import java.util.UUID;

public class RuleChainId extends UUIDBased implements EntityId {

    @JsonCreator
    public RuleChainId(@JsonProperty("id") UUID id) {
        super(id);
    }

    @JsonIgnore
    @Override
    public EntityType getEntityType() {
        return EntityType.RULE_CHAIN;
    }
}
