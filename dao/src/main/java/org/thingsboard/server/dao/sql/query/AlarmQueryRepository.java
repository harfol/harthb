package org.thingsboard.server.dao.sql.query;

import org.thingsboard.server.common.data.id.CustomerId;
import org.thingsboard.server.common.data.id.EntityId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.query.AlarmData;
import org.thingsboard.server.common.data.query.AlarmDataPageLink;
import org.thingsboard.server.common.data.query.AlarmDataQuery;

import java.util.Collection;

public interface AlarmQueryRepository {

    PageData<AlarmData> findAlarmDataByQueryForEntities(TenantId tenantId, CustomerId customerId,
                                                        AlarmDataQuery query, Collection<EntityId> orderedEntityIds);

}
