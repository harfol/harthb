package org.thingsboard.server.common.transport.auth;

import lombok.Builder;
import lombok.Data;
import org.thingsboard.server.common.data.DeviceProfile;

@Data
@Builder
public class ValidateDeviceCredentialsResponse implements DeviceProfileAware {

    private final TransportDeviceInfo deviceInfo;
    private final DeviceProfile deviceProfile;
    private final String credentials;

    public boolean hasDeviceInfo() {
        return deviceInfo != null;
    }
}
