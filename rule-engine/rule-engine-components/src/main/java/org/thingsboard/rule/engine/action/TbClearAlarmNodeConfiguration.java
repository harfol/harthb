package org.thingsboard.rule.engine.action;

import lombok.Data;
import org.thingsboard.rule.engine.api.NodeConfiguration;
import org.thingsboard.server.common.data.alarm.AlarmSeverity;

@Data
public class TbClearAlarmNodeConfiguration extends TbAbstractAlarmNodeConfiguration implements NodeConfiguration<TbClearAlarmNodeConfiguration> {

    @Override
    public TbClearAlarmNodeConfiguration defaultConfiguration() {
        TbClearAlarmNodeConfiguration configuration = new TbClearAlarmNodeConfiguration();
        configuration.setAlarmDetailsBuildJs("var details = {};\n" +
                "if (metadata.prevAlarmDetails) {\n" +
                "    details = JSON.parse(metadata.prevAlarmDetails);\n" +
                "}\n" +
                "return details;");
        configuration.setAlarmType("General Alarm");
        return configuration;
    }
}
