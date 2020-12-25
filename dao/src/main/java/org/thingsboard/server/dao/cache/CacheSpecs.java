package org.thingsboard.server.dao.cache;

import lombok.Data;

@Data
public class CacheSpecs {
    private Integer timeToLiveInMinutes;
    private Integer maxSize;
}
