package org.thingsboard.server.common.data.device.profile;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class SpecificTimeSchedule implements AlarmSchedule {

    private String timezone;
    private Set<Integer> daysOfWeek;
    private long startsOn;
    private long endsOn;

    @Override
    public AlarmScheduleType getType() {
        return AlarmScheduleType.SPECIFIC_TIME;
    }

}
