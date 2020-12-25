package org.thingsboard.server.queue.settings;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Data
@EnableAutoConfiguration
@Configuration
@ConfigurationProperties(prefix = "queue.rule-engine")
public class TbQueueRuleEngineSettings {

    private String topic;
    private List<TbRuleEngineQueueConfiguration> queues;

    //TODO 2.5 ybondarenko: make sure the queue names are valid to all queue providers.
    // See how they are used in TbRuleEngineQueueFactory.createToRuleEngineMsgConsumer and all producers
    @PostConstruct
    public void validate() {
        queues.stream().filter(queue -> queue.getName().equals("Main")).findFirst().orElseThrow(() -> {
            log.error("Main queue is not configured in thingsboard.yml");
            return new RuntimeException("No \"Main\" queue configured!");
        });
    }

}
