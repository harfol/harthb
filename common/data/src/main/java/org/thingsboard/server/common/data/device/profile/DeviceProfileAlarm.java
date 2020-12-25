package org.thingsboard.server.common.data.device.profile;

import lombok.Data;
import org.thingsboard.server.common.data.alarm.AlarmSeverity;

import java.util.List;
import java.util.TreeMap;

@Data
public class DeviceProfileAlarm {

    private String id;
    private String alarmType;

    private TreeMap<AlarmSeverity, AlarmRule> createRules;
    private AlarmRule clearRule;

    // Hidden in advanced settings
    private boolean propagate;
    private List<String> propagateRelationTypes;
}
