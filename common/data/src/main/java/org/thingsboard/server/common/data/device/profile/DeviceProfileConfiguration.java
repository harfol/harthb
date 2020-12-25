package org.thingsboard.server.common.data.device.profile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.thingsboard.server.common.data.DeviceProfileType;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
         @JsonSubTypes.Type(value = DefaultDeviceProfileConfiguration.class, name = "DEFAULT")})
public interface DeviceProfileConfiguration {

    @JsonIgnore
    DeviceProfileType getType();

}
