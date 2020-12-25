package org.thingsboard.server.common.data.kv;

import lombok.Data;

@Data
public class BaseTsKvQuery implements TsKvQuery {

    private final String key;
    private final long startTs;
    private final long endTs;

    public BaseTsKvQuery(String key, long startTs, long endTs) {
        this.key = key;
        this.startTs = startTs;
        this.endTs = endTs;
    }

}
