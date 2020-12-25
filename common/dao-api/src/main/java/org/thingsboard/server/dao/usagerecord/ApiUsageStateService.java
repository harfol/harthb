package org.thingsboard.server.dao.usagerecord;

import org.thingsboard.server.common.data.ApiUsageState;
import org.thingsboard.server.common.data.id.ApiUsageStateId;
import org.thingsboard.server.common.data.id.TenantId;

public interface ApiUsageStateService {

    ApiUsageState createDefaultApiUsageState(TenantId id);

    ApiUsageState update(ApiUsageState apiUsageState);

    ApiUsageState findTenantApiUsageState(TenantId tenantId);

    void deleteApiUsageStateByTenantId(TenantId tenantId);

    ApiUsageState findApiUsageStateById(TenantId tenantId, ApiUsageStateId id);
}
