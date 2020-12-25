package org.thingsboard.server.dao.alarm;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.thingsboard.server.common.data.alarm.Alarm;
import org.thingsboard.server.common.data.id.EntityId;

import java.util.Collections;
import java.util.List;

@Data
public class AlarmOperationResult {
    private final Alarm alarm;
    private final boolean successful;
    private final List<EntityId> propagatedEntitiesList;

    public AlarmOperationResult(Alarm alarm, boolean successful) {
        this.alarm = alarm;
        this.successful = successful;
        this.propagatedEntitiesList = Collections.emptyList();
    }

    public AlarmOperationResult(Alarm alarm, boolean successful, List<EntityId> propagatedEntitiesList) {
        this.alarm = alarm;
        this.successful = successful;
        this.propagatedEntitiesList = propagatedEntitiesList;
    }
}
