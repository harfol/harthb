package org.thingsboard.server.dao.alarm;

import com.google.common.util.concurrent.ListenableFuture;
import org.thingsboard.server.common.data.alarm.Alarm;
import org.thingsboard.server.common.data.alarm.AlarmInfo;
import org.thingsboard.server.common.data.alarm.AlarmQuery;
import org.thingsboard.server.common.data.id.CustomerId;
import org.thingsboard.server.common.data.id.EntityId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.query.AlarmData;
import org.thingsboard.server.common.data.query.AlarmDataPageLink;
import org.thingsboard.server.common.data.query.AlarmDataQuery;
import org.thingsboard.server.dao.Dao;

import java.util.Collection;
import java.util.UUID;

/**
 * Created by ashvayka on 11.05.17.
 */
public interface AlarmDao extends Dao<Alarm> {

    Boolean deleteAlarm(TenantId tenantId, Alarm alarm);

    ListenableFuture<Alarm> findLatestByOriginatorAndType(TenantId tenantId, EntityId originator, String type);

    ListenableFuture<Alarm> findAlarmByIdAsync(TenantId tenantId, UUID key);

    Alarm save(TenantId tenantId, Alarm alarm);

    PageData<AlarmInfo> findAlarms(TenantId tenantId, AlarmQuery query);

    PageData<AlarmData> findAlarmDataByQueryForEntities(TenantId tenantId, CustomerId customerId,
                                                        AlarmDataQuery query, Collection<EntityId> orderedEntityIds);
}
