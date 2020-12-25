package org.thingsboard.rule.engine.telemetry;

import lombok.Data;
import org.thingsboard.rule.engine.api.NodeConfiguration;
import org.thingsboard.server.common.data.DataConstants;

@Data
public class TbMsgAttributesNodeConfiguration implements NodeConfiguration<TbMsgAttributesNodeConfiguration> {

    private String scope;

    private Boolean notifyDevice;

    @Override
    public TbMsgAttributesNodeConfiguration defaultConfiguration() {
        TbMsgAttributesNodeConfiguration configuration = new TbMsgAttributesNodeConfiguration();
        configuration.setScope(DataConstants.SERVER_SCOPE);
        configuration.setNotifyDevice(false);
        return configuration;
    }
}
