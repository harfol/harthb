package org.thingsboard.server.queue.kafka;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@ConditionalOnProperty(prefix = "queue", value = "type", havingValue = "kafka")
public class TbKafkaTopicConfigs {
    @Value("${queue.kafka.topic-properties.core}")
    private String coreProperties;
    @Value("${queue.kafka.topic-properties.rule-engine}")
    private String ruleEngineProperties;
    @Value("${queue.kafka.topic-properties.transport-api}")
    private String transportApiProperties;
    @Value("${queue.kafka.topic-properties.notifications}")
    private String notificationsProperties;
    @Value("${queue.kafka.topic-properties.js-executor}")
    private String jsExecutorProperties;

    @Getter
    private Map<String, String> coreConfigs;
    @Getter
    private Map<String, String> ruleEngineConfigs;
    @Getter
    private Map<String, String> transportApiConfigs;
    @Getter
    private Map<String, String> notificationsConfigs;
    @Getter
    private Map<String, String> jsExecutorConfigs;

    @PostConstruct
    private void init() {
        coreConfigs = getConfigs(coreProperties);
        ruleEngineConfigs = getConfigs(ruleEngineProperties);
        transportApiConfigs = getConfigs(transportApiProperties);
        notificationsConfigs = getConfigs(notificationsProperties);
        jsExecutorConfigs = getConfigs(jsExecutorProperties);
    }

    private Map<String, String> getConfigs(String properties) {
        Map<String, String> configs = new HashMap<>();
        for (String property : properties.split(";")) {
            int delimiterPosition = property.indexOf(":");
            String key = property.substring(0, delimiterPosition);
            String value = property.substring(delimiterPosition + 1);
            configs.put(key, value);
        }
        return configs;
    }
}
