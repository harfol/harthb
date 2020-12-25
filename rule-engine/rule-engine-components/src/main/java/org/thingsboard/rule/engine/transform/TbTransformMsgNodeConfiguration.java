package org.thingsboard.rule.engine.transform;

import lombok.Data;
import org.thingsboard.rule.engine.api.NodeConfiguration;

@Data
public class TbTransformMsgNodeConfiguration extends TbTransformNodeConfiguration implements NodeConfiguration {

    private String jsScript;

    @Override
    public TbTransformMsgNodeConfiguration defaultConfiguration() {
        TbTransformMsgNodeConfiguration configuration = new TbTransformMsgNodeConfiguration();
        configuration.setJsScript("return {msg: msg, metadata: metadata, msgType: msgType};");
        return configuration;
    }
}
