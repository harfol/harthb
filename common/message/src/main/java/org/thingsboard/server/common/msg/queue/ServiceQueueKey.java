package org.thingsboard.server.common.msg.queue;

import lombok.Getter;
import lombok.ToString;
import org.thingsboard.server.common.data.id.TenantId;

import java.util.Objects;

@ToString
public class ServiceQueueKey {
    @Getter
    private final ServiceQueue serviceQueue;

    @Getter
    private final TenantId tenantId;

    public ServiceQueueKey(ServiceQueue serviceQueue, TenantId tenantId) {
        this.serviceQueue = serviceQueue;
        this.tenantId = tenantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceQueueKey that = (ServiceQueueKey) o;
        return serviceQueue.equals(that.serviceQueue) &&
                Objects.equals(tenantId, that.tenantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceQueue, tenantId);
    }

    public ServiceType getServiceType() {
        return serviceQueue.getType();
    }
}
