package org.thingsboard.server.common.data.device.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.concurrent.TimeUnit;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DurationAlarmConditionSpec implements AlarmConditionSpec {

    private TimeUnit unit;
    private long value;

    @Override
    public AlarmConditionSpecType getType() {
        return AlarmConditionSpecType.DURATION;
    }
}
