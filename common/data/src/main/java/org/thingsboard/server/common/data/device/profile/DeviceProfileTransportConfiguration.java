package org.thingsboard.server.common.data.device.profile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.thingsboard.server.common.data.DeviceProfileType;
import org.thingsboard.server.common.data.DeviceTransportType;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
         @JsonSubTypes.Type(value = DefaultDeviceProfileTransportConfiguration.class, name = "DEFAULT"),
         @JsonSubTypes.Type(value = MqttDeviceProfileTransportConfiguration.class, name = "MQTT"),
         @JsonSubTypes.Type(value = Lwm2mDeviceProfileTransportConfiguration.class, name = "LWM2M")})
public interface DeviceProfileTransportConfiguration {

    @JsonIgnore
    DeviceTransportType getType();

}
