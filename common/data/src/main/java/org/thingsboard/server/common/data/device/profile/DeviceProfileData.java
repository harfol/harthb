package org.thingsboard.server.common.data.device.profile;

import lombok.Data;

import java.util.List;

@Data
public class DeviceProfileData {

    private DeviceProfileConfiguration configuration;
    private DeviceProfileTransportConfiguration transportConfiguration;
    private DeviceProfileProvisionConfiguration provisionConfiguration;
    private List<DeviceProfileAlarm> alarms;

}
