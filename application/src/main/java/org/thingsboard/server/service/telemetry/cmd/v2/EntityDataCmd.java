package org.thingsboard.server.service.telemetry.cmd.v2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.thingsboard.server.common.data.query.EntityDataQuery;

public class EntityDataCmd extends DataCmd {

    @Getter
    private final EntityDataQuery query;
    @Getter
    private final EntityHistoryCmd historyCmd;
    @Getter
    private final LatestValueCmd latestCmd;
    @Getter
    private final TimeSeriesCmd tsCmd;

    @JsonCreator
    public EntityDataCmd(@JsonProperty("cmdId") int cmdId,
                         @JsonProperty("query") EntityDataQuery query,
                         @JsonProperty("historyCmd") EntityHistoryCmd historyCmd,
                         @JsonProperty("latestCmd") LatestValueCmd latestCmd,
                         @JsonProperty("tsCmd") TimeSeriesCmd tsCmd) {
        super(cmdId);
        this.query = query;
        this.historyCmd = historyCmd;
        this.latestCmd = latestCmd;
        this.tsCmd = tsCmd;
    }
}
