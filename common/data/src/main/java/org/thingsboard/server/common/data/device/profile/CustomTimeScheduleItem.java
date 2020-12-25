package org.thingsboard.server.common.data.device.profile;

import lombok.Data;

import java.util.List;

@Data
public class CustomTimeScheduleItem {

    private boolean enabled;
    private int dayOfWeek;
    private long startsOn;
    private long endsOn;

}
