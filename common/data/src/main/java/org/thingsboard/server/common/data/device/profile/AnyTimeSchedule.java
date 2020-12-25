package org.thingsboard.server.common.data.device.profile;

public class AnyTimeSchedule implements AlarmSchedule {

    @Override
    public AlarmScheduleType getType() {
        return AlarmScheduleType.ANY_TIME;
    }

}
