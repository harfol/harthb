package org.thingsboard.server.service.queue.processing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thingsboard.server.queue.settings.TbRuleEngineQueueSubmitStrategyConfiguration;

@Component
@Slf4j
public class TbRuleEngineSubmitStrategyFactory {

    public TbRuleEngineSubmitStrategy newInstance(String name, TbRuleEngineQueueSubmitStrategyConfiguration configuration) {
        switch (configuration.getType()) {
            case "BURST":
                return new BurstTbRuleEngineSubmitStrategy(name);
            case "BATCH":
                return new BatchTbRuleEngineSubmitStrategy(name, configuration.getBatchSize());
            case "SEQUENTIAL_BY_ORIGINATOR":
                return new SequentialByOriginatorIdTbRuleEngineSubmitStrategy(name);
            case "SEQUENTIAL_BY_TENANT":
                return new SequentialByTenantIdTbRuleEngineSubmitStrategy(name);
            case "SEQUENTIAL":
                return new SequentialTbRuleEngineSubmitStrategy(name);
            default:
                throw new RuntimeException("TbRuleEngineProcessingStrategy with type " + configuration.getType() + " is not supported!");
        }
    }

}
