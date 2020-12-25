package org.thingsboard.rule.engine.filter;

import lombok.Data;
import org.thingsboard.rule.engine.api.NodeConfiguration;

@Data
public class TbJsFilterNodeConfiguration implements NodeConfiguration<TbJsFilterNodeConfiguration> {

    private String jsScript;

    @Override
    public TbJsFilterNodeConfiguration defaultConfiguration() {
        TbJsFilterNodeConfiguration configuration = new TbJsFilterNodeConfiguration();
        configuration.setJsScript("return msg.temperature > 20;");
        return configuration;
    }
}
