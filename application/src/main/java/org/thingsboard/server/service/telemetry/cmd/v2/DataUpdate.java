package org.thingsboard.server.service.telemetry.cmd.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.service.telemetry.sub.SubscriptionErrorCode;

import java.util.List;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class DataUpdate<T> {

    private final int cmdId;
    private final PageData<T> data;
    private final List<T> update;
    private final int errorCode;
    private final String errorMsg;

    public DataUpdate(int cmdId, PageData<T> data, List<T> update) {
        this(cmdId, data, update, SubscriptionErrorCode.NO_ERROR.getCode(), null);
    }

    public DataUpdate(int cmdId, int errorCode, String errorMsg) {
        this(cmdId, null, null, errorCode, errorMsg);
    }

    public abstract DataUpdateType getDataUpdateType();
}
