package org.thingsboard.server.common.data.kv;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents time series KV data entry
 * 
 * @author ashvayka
 *
 */
public interface TsKvEntry extends KvEntry {

    long getTs();

    @JsonIgnore
    int getDataPoints();

}
