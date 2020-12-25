package org.thingsboard.server.service.telemetry.cmd.v2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.query.EntityData;
import org.thingsboard.server.service.telemetry.sub.SubscriptionErrorCode;

import java.util.List;


public class EntityDataUpdate extends DataUpdate<EntityData> {

    @Getter
    private long allowedEntities;

    public EntityDataUpdate(int cmdId, PageData<EntityData> data, List<EntityData> update, long allowedEntities) {
        super(cmdId, data, update, SubscriptionErrorCode.NO_ERROR.getCode(), null);
        this.allowedEntities = allowedEntities;
    }

    public EntityDataUpdate(int cmdId, int errorCode, String errorMsg) {
        super(cmdId, null, null, errorCode, errorMsg);
    }

    @Override
    public DataUpdateType getDataUpdateType() {
        return DataUpdateType.ENTITY_DATA;
    }

    @JsonCreator
    public EntityDataUpdate(@JsonProperty("cmdId") int cmdId,
                            @JsonProperty("data") PageData<EntityData> data,
                            @JsonProperty("update") List<EntityData> update,
                            @JsonProperty("errorCode") int errorCode,
                            @JsonProperty("errorMsg") String errorMsg) {
        super(cmdId, data, update, errorCode, errorMsg);
    }

}
