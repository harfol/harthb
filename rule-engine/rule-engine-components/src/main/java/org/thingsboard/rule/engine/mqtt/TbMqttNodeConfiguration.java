package org.thingsboard.rule.engine.mqtt;

import lombok.Data;
import org.thingsboard.rule.engine.api.NodeConfiguration;
import org.thingsboard.rule.engine.mqtt.credentials.AnonymousCredentials;
import org.thingsboard.rule.engine.mqtt.credentials.MqttClientCredentials;

@Data
public class TbMqttNodeConfiguration implements NodeConfiguration<TbMqttNodeConfiguration> {

    private String topicPattern;
    private String host;
    private int port;
    private int connectTimeoutSec;
    private String clientId;

    private boolean cleanSession;
    private boolean ssl;
    private MqttClientCredentials credentials;

    @Override
    public TbMqttNodeConfiguration defaultConfiguration() {
        TbMqttNodeConfiguration configuration = new TbMqttNodeConfiguration();
        configuration.setTopicPattern("my-topic");
        configuration.setHost("localhost");
        configuration.setPort(1883);
        configuration.setConnectTimeoutSec(10);
        configuration.setCleanSession(true);
        configuration.setSsl(false);
        configuration.setCredentials(new AnonymousCredentials());
        return configuration;
    }

}
