package org.thingsboard.server.queue.discovery;

import org.thingsboard.server.common.data.id.EntityId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.msg.queue.ServiceType;
import org.thingsboard.server.common.msg.queue.TopicPartitionInfo;
import org.thingsboard.server.gen.transport.TransportProtos;

import java.util.List;
import java.util.Set;

/**
 * Once application is ready or cluster topology changes, this Service will produce {@link PartitionChangeEvent}
 */
public interface PartitionService {

    TopicPartitionInfo resolve(ServiceType serviceType, TenantId tenantId, EntityId entityId);

    TopicPartitionInfo resolve(ServiceType serviceType, String queueName, TenantId tenantId, EntityId entityId);

    /**
     * Received from the Discovery service when network topology is changed.
     * @param currentService - current service information {@link org.thingsboard.server.gen.transport.TransportProtos.ServiceInfo}
     * @param otherServices - all other discovered services {@link org.thingsboard.server.gen.transport.TransportProtos.ServiceInfo}
     */
    void recalculatePartitions(TransportProtos.ServiceInfo currentService, List<TransportProtos.ServiceInfo> otherServices);

    /**
     * Get all active service ids by service type
     * @param serviceType to filter the list of services
     * @return list of all active services
     */
    Set<String> getAllServiceIds(ServiceType serviceType);

    /**
     * Each Service should start a consumer for messages that target individual service instance based on serviceId.
     * This topic is likely to have single partition, and is always assigned to the service.
     * @param serviceType
     * @param serviceId
     * @return
     */
    TopicPartitionInfo getNotificationsTopic(ServiceType serviceType, String serviceId);
}
