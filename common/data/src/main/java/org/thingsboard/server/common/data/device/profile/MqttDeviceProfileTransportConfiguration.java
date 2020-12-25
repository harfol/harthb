package org.thingsboard.server.common.data.device.profile;

import lombok.Data;
import org.thingsboard.server.common.data.DeviceTransportType;

@Data
public class MqttDeviceProfileTransportConfiguration implements DeviceProfileTransportConfiguration {

    private String deviceTelemetryTopic = MqttTopics.DEVICE_TELEMETRY_TOPIC;
    private String deviceAttributesTopic = MqttTopics.DEVICE_ATTRIBUTES_TOPIC;
    private TransportPayloadTypeConfiguration transportPayloadTypeConfiguration;

    @Override
    public DeviceTransportType getType() {
        return DeviceTransportType.MQTT;
    }

    public TransportPayloadTypeConfiguration getTransportPayloadTypeConfiguration() {
        if (transportPayloadTypeConfiguration != null) {
            return transportPayloadTypeConfiguration;
        } else {
            return new JsonTransportPayloadConfiguration();
        }
    }


}
