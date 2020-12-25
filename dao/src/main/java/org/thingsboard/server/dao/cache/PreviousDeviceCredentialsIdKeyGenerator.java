package org.thingsboard.server.dao.cache;

import org.springframework.cache.interceptor.KeyGenerator;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.security.DeviceCredentials;
import org.thingsboard.server.dao.device.DeviceCredentialsService;

import java.lang.reflect.Method;

import static org.thingsboard.server.common.data.CacheConstants.DEVICE_CREDENTIALS_CACHE;

public class PreviousDeviceCredentialsIdKeyGenerator implements KeyGenerator {

    private static final String NOT_VALID_DEVICE = DEVICE_CREDENTIALS_CACHE + "_notValidDeviceCredentialsId";

    @Override
    public Object generate(Object o, Method method, Object... objects) {
        DeviceCredentialsService deviceCredentialsService = (DeviceCredentialsService) o;
        TenantId tenantId = (TenantId) objects[0];
        DeviceCredentials deviceCredentials = (DeviceCredentials) objects[1];
        if (deviceCredentials.getDeviceId() != null) {
            DeviceCredentials oldDeviceCredentials = deviceCredentialsService.findDeviceCredentialsByDeviceId(tenantId, deviceCredentials.getDeviceId());
            if (oldDeviceCredentials != null) {
                return DEVICE_CREDENTIALS_CACHE + "_" + oldDeviceCredentials.getCredentialsId();
            }
        }
        return NOT_VALID_DEVICE;
    }
}
