package org.thingsboard.server.common.data.device.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.thingsboard.server.common.data.query.KeyFilter;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlarmCondition {

    private List<KeyFilter> condition;
    private AlarmConditionSpec spec;

}
