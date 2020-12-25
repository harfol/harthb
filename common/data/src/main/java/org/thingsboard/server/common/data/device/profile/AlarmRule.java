package org.thingsboard.server.common.data.device.profile;

import lombok.Data;

@Data
public class AlarmRule {

    private AlarmCondition condition;
    private AlarmSchedule schedule;
    // Advanced
    private String alarmDetails;

}
