package org.thingsboard.rule.engine.mqtt.azure;

import lombok.Data;
import org.thingsboard.rule.engine.api.NodeConfiguration;
import org.thingsboard.rule.engine.mqtt.TbMqttNodeConfiguration;
import org.thingsboard.rule.engine.mqtt.credentials.AnonymousCredentials;
import org.thingsboard.rule.engine.mqtt.credentials.MqttClientCredentials;

@Data
public class TbAzureIotHubNodeConfiguration extends TbMqttNodeConfiguration {

    @Override
    public TbAzureIotHubNodeConfiguration defaultConfiguration() {
        TbAzureIotHubNodeConfiguration configuration = new TbAzureIotHubNodeConfiguration();
        configuration.setTopicPattern("devices/<device_id>/messages/events/");
        configuration.setHost("<iot-hub-name>.azure-devices.net");
        configuration.setPort(8883);
        configuration.setConnectTimeoutSec(10);
        configuration.setCleanSession(true);
        configuration.setSsl(true);
        configuration.setCredentials(new AzureIotHubSasCredentials());
        return configuration;
    }

}
