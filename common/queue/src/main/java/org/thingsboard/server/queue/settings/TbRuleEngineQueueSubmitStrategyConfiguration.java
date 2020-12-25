package org.thingsboard.server.queue.settings;

import lombok.Data;

@Data
public class TbRuleEngineQueueSubmitStrategyConfiguration {

    private String type;
    private int batchSize;

}
