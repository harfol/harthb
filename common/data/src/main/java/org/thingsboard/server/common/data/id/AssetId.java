package org.thingsboard.server.common.data.id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.thingsboard.server.common.data.EntityType;

import java.util.UUID;

public class AssetId extends UUIDBased implements EntityId {

    private static final long serialVersionUID = 1L;

    @JsonCreator
    public AssetId(@JsonProperty("id") UUID id) {
        super(id);
    }

    public static AssetId fromString(String assetId) {
        return new AssetId(UUID.fromString(assetId));
    }

    @JsonIgnore
    @Override
    public EntityType getEntityType() {
        return EntityType.ASSET;
    }
}
