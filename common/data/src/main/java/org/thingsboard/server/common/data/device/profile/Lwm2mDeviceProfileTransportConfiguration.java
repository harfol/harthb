package org.thingsboard.server.common.data.device.profile;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.thingsboard.server.common.data.DeviceProfileType;
import org.thingsboard.server.common.data.DeviceTransportType;

import java.util.HashMap;
import java.util.Map;

@Data
public class Lwm2mDeviceProfileTransportConfiguration implements DeviceProfileTransportConfiguration {

    @JsonIgnore
    private Map<String, Object> properties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> properties() {
        return this.properties;
    }

    @JsonAnySetter
    public void put(String name, Object value) {
        this.properties.put(name, value);
    }

    @Override
    public DeviceTransportType getType() {
        return DeviceTransportType.LWM2M;
    }

}
