package org.thingsboard.server.service.apiusage;

import org.springframework.context.ApplicationListener;
import org.thingsboard.server.common.data.ApiUsageState;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.id.TenantProfileId;
import org.thingsboard.server.common.msg.queue.TbCallback;
import org.thingsboard.server.gen.transport.TransportProtos.ToUsageStatsServiceMsg;
import org.thingsboard.server.queue.common.TbProtoQueueMsg;
import org.thingsboard.server.queue.discovery.PartitionChangeEvent;

public interface TbApiUsageStateService extends ApplicationListener<PartitionChangeEvent> {

    void process(TbProtoQueueMsg<ToUsageStatsServiceMsg> msg, TbCallback callback);

    ApiUsageState getApiUsageState(TenantId tenantId);

    void onTenantProfileUpdate(TenantProfileId tenantProfileId);

    void onTenantUpdate(TenantId tenantId);

    void onApiUsageStateUpdate(TenantId tenantId);
}
