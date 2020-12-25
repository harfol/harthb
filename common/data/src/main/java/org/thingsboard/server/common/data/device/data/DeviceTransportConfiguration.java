package org.thingsboard.server.common.data.device.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.thingsboard.server.common.data.DeviceTransportType;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DefaultDeviceTransportConfiguration.class, name = "DEFAULT"),
        @JsonSubTypes.Type(value = MqttDeviceTransportConfiguration.class, name = "MQTT"),
        @JsonSubTypes.Type(value = Lwm2mDeviceTransportConfiguration.class, name = "LWM2M")})
public interface DeviceTransportConfiguration {

    @JsonIgnore
    DeviceTransportType getType();

}
