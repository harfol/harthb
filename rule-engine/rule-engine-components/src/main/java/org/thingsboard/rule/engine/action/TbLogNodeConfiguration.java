package org.thingsboard.rule.engine.action;

import lombok.Data;
import org.thingsboard.rule.engine.api.NodeConfiguration;

@Data
public class TbLogNodeConfiguration implements NodeConfiguration {

    private String jsScript;

    @Override
    public TbLogNodeConfiguration defaultConfiguration() {
        TbLogNodeConfiguration configuration = new TbLogNodeConfiguration();
        configuration.setJsScript("return 'Incoming message:\\n' + JSON.stringify(msg) + '\\nIncoming metadata:\\n' + JSON.stringify(metadata);");
        return configuration;
    }
}
