package org.thingsboard.server.service.session;

import org.thingsboard.server.common.data.id.DeviceId;
import org.thingsboard.server.gen.transport.TransportProtos.DeviceSessionsCacheEntry;

/**
 * Created by ashvayka on 29.10.18.
 */
public interface DeviceSessionCacheService {

    byte[] get(DeviceId deviceId);

    byte[] put(DeviceId deviceId, byte[] sessions);

}
