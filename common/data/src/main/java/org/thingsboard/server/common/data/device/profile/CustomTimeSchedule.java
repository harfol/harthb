package org.thingsboard.server.common.data.device.profile;

import lombok.Data;

import java.util.List;

@Data
public class CustomTimeSchedule implements AlarmSchedule {

    private String timezone;
    private List<CustomTimeScheduleItem> items;

    @Override
    public AlarmScheduleType getType() {
        return AlarmScheduleType.CUSTOM;
    }

}
