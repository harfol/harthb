package org.thingsboard.server.dao.timeseries;

import lombok.Data;

@Data
public class PsqlPartition {

    private static final String TABLE_REGEX = "ts_kv_";

    private long start;
    private long end;
    private String partitionDate;
    private String query;

    public PsqlPartition(long start, long end, String partitionDate) {
        this.start = start;
        this.end = end;
        this.partitionDate = partitionDate;
        this.query = createStatement(start, end, partitionDate);
    }

    private String createStatement(long start, long end, String partitionDate) {
        return "CREATE TABLE IF NOT EXISTS " + TABLE_REGEX + partitionDate + " PARTITION OF ts_kv FOR VALUES FROM (" + start + ") TO (" + end + ")";
    }
}