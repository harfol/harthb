package org.thingsboard.server.common.data.device.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.concurrent.TimeUnit;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepeatingAlarmConditionSpec implements AlarmConditionSpec {

    private int count;

    @Override
    public AlarmConditionSpecType getType() {
        return AlarmConditionSpecType.REPEATING;
    }
}
