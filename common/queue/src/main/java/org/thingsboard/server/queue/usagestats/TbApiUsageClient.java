package org.thingsboard.server.queue.usagestats;

import org.thingsboard.server.common.data.ApiUsageRecordKey;
import org.thingsboard.server.common.data.id.TenantId;

public interface TbApiUsageClient {

    void report(TenantId tenantId, ApiUsageRecordKey key, long value);

    void report(TenantId tenantId, ApiUsageRecordKey key);

}
