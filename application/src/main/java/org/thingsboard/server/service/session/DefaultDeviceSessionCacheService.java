package org.thingsboard.server.service.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.thingsboard.server.common.data.id.DeviceId;
import org.thingsboard.server.gen.transport.TransportProtos.DeviceSessionsCacheEntry;
import org.thingsboard.server.queue.util.TbCoreComponent;

import java.util.Collections;

import static org.thingsboard.server.common.data.CacheConstants.SESSIONS_CACHE;

/**
 * Created by ashvayka on 29.10.18.
 */
@Service
@TbCoreComponent
@Slf4j
public class DefaultDeviceSessionCacheService implements DeviceSessionCacheService {

    @Override
    @Cacheable(cacheNames = SESSIONS_CACHE, key = "#deviceId.toString()")
    public byte[] get(DeviceId deviceId) {
        log.debug("[{}] Fetching session data from cache", deviceId);
        return DeviceSessionsCacheEntry.newBuilder().addAllSessions(Collections.emptyList()).build().toByteArray();
    }

    @Override
    @CachePut(cacheNames = SESSIONS_CACHE, key = "#deviceId.toString()")
    public byte[] put(DeviceId deviceId, byte[] sessions) {
        log.debug("[{}] Pushing session data to cache: {}", deviceId, sessions);
        return sessions;
    }
}
