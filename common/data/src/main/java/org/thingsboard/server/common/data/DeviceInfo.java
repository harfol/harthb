package org.thingsboard.server.common.data;

import lombok.Data;
import org.thingsboard.server.common.data.id.DeviceId;

@Data
public class DeviceInfo extends Device {

    private String customerTitle;
    private boolean customerIsPublic;
    private String deviceProfileName;

    public DeviceInfo() {
        super();
    }

    public DeviceInfo(DeviceId deviceId) {
        super(deviceId);
    }

    public DeviceInfo(Device device, String customerTitle, boolean customerIsPublic, String deviceProfileName) {
        super(device);
        this.customerTitle = customerTitle;
        this.customerIsPublic = customerIsPublic;
        this.deviceProfileName = deviceProfileName;
    }
}
