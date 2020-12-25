package org.thingsboard.rule.engine.action;

import lombok.Data;

@Data
public abstract class TbAbstractAlarmNodeConfiguration {

    private String alarmType;
    private String alarmDetailsBuildJs;

}
