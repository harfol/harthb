package org.thingsboard.server.common.data.device.data;

import lombok.Data;
import org.thingsboard.server.common.data.DeviceProfileType;

@Data
public class DefaultDeviceConfiguration implements DeviceConfiguration {

    @Override
    public DeviceProfileType getType() {
        return DeviceProfileType.DEFAULT;
    }

}
