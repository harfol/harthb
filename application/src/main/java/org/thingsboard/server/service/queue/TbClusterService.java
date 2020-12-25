package org.thingsboard.server.service.queue;

import org.thingsboard.rule.engine.api.msg.ToDeviceActorNotificationMsg;
import org.thingsboard.server.common.data.ApiUsageState;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.data.DeviceProfile;
import org.thingsboard.server.common.data.Tenant;
import org.thingsboard.server.common.data.TenantProfile;
import org.thingsboard.server.common.data.id.EntityId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.plugin.ComponentLifecycleEvent;
import org.thingsboard.server.common.msg.TbMsg;
import org.thingsboard.server.common.msg.queue.TopicPartitionInfo;
import org.thingsboard.server.gen.transport.TransportProtos;
import org.thingsboard.server.gen.transport.TransportProtos.ToCoreMsg;
import org.thingsboard.server.gen.transport.TransportProtos.ToTransportMsg;
import org.thingsboard.server.queue.TbQueueCallback;
import org.thingsboard.server.service.rpc.FromDeviceRpcResponse;

import java.util.UUID;

public interface TbClusterService {

    void pushMsgToCore(TopicPartitionInfo tpi, UUID msgKey, ToCoreMsg msg, TbQueueCallback callback);

    void pushMsgToCore(TenantId tenantId, EntityId entityId, ToCoreMsg msg, TbQueueCallback callback);

    void pushMsgToCore(ToDeviceActorNotificationMsg msg, TbQueueCallback callback);

    void pushNotificationToCore(String targetServiceId, FromDeviceRpcResponse response, TbQueueCallback callback);

    void pushMsgToRuleEngine(TopicPartitionInfo tpi, UUID msgId, TransportProtos.ToRuleEngineMsg msg, TbQueueCallback callback);

    void pushMsgToRuleEngine(TenantId tenantId, EntityId entityId, TbMsg msg, TbQueueCallback callback);

    void pushNotificationToRuleEngine(String targetServiceId, FromDeviceRpcResponse response, TbQueueCallback callback);

    void pushNotificationToTransport(String targetServiceId, ToTransportMsg response, TbQueueCallback callback);

    void onEntityStateChange(TenantId tenantId, EntityId entityId, ComponentLifecycleEvent state);

    void onDeviceProfileChange(DeviceProfile deviceProfile, TbQueueCallback callback);

    void onDeviceProfileDelete(DeviceProfile deviceProfile, TbQueueCallback callback);

    void onTenantProfileChange(TenantProfile tenantProfile, TbQueueCallback callback);

    void onTenantProfileDelete(TenantProfile tenantProfile, TbQueueCallback callback);

    void onTenantChange(Tenant tenant, TbQueueCallback callback);

    void onTenantDelete(Tenant tenant, TbQueueCallback callback);

    void onApiStateChange(ApiUsageState apiUsageState, TbQueueCallback callback);

    void onDeviceChange(Device device, TbQueueCallback callback);

    void onDeviceDeleted(Device device, TbQueueCallback callback);
}
