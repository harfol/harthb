package org.thingsboard.rule.engine.telemetry;

import lombok.Data;
import org.thingsboard.rule.engine.api.NodeConfiguration;

@Data
public class TbMsgTimeseriesNodeConfiguration implements NodeConfiguration<TbMsgTimeseriesNodeConfiguration> {

    private long defaultTTL;

    @Override
    public TbMsgTimeseriesNodeConfiguration defaultConfiguration() {
        TbMsgTimeseriesNodeConfiguration configuration = new TbMsgTimeseriesNodeConfiguration();
        configuration.setDefaultTTL(0L);
        return configuration;
    }
}
