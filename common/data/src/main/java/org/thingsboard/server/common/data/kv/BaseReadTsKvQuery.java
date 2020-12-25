package org.thingsboard.server.common.data.kv;

import lombok.Data;

@Data
public class BaseReadTsKvQuery extends BaseTsKvQuery implements ReadTsKvQuery {

    private final long interval;
    private final int limit;
    private final Aggregation aggregation;
    private final String order;

    public BaseReadTsKvQuery(String key, long startTs, long endTs, long interval, int limit, Aggregation aggregation) {
        this(key, startTs, endTs, interval, limit, aggregation, "DESC");
    }

    public BaseReadTsKvQuery(String key, long startTs, long endTs, long interval, int limit, Aggregation aggregation,
                             String order) {
        super(key, startTs, endTs);
        this.interval = interval;
        this.limit = limit;
        this.aggregation = aggregation;
        this.order = order;
    }

    public BaseReadTsKvQuery(String key, long startTs, long endTs) {
        this(key, startTs, endTs, endTs - startTs, 1, Aggregation.AVG, "DESC");
    }

    public BaseReadTsKvQuery(String key, long startTs, long endTs, int limit, String order) {
        this(key, startTs, endTs, endTs - startTs, limit, Aggregation.NONE, order);
    }

}
