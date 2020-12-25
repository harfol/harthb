package org.thingsboard.server.common.transport.auth;

import lombok.Data;
import org.thingsboard.server.common.data.id.DeviceId;
import org.thingsboard.server.common.data.id.DeviceProfileId;
import org.thingsboard.server.common.data.id.TenantId;

@Data
public class TransportDeviceInfo {

    private TenantId tenantId;
    private DeviceProfileId deviceProfileId;
    private DeviceId deviceId;
    private String deviceName;
    private String deviceType;
    private String additionalInfo;

}
