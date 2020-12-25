package org.thingsboard.server.common.data.device.data;

import lombok.Data;

@Data
public class DeviceData {

    private DeviceConfiguration configuration;
    private DeviceTransportConfiguration transportConfiguration;

}
