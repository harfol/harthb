package org.thingsboard.server.queue.discovery;

import lombok.AllArgsConstructor;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.msg.queue.ServiceQueue;

import java.util.Objects;

@AllArgsConstructor
public class TopicPartitionInfoKey {
    private ServiceQueue serviceQueue;
    private TenantId isolatedTenantId;
    private int partition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicPartitionInfoKey that = (TopicPartitionInfoKey) o;
        return partition == that.partition &&
                serviceQueue.equals(that.serviceQueue) &&
                Objects.equals(isolatedTenantId, that.isolatedTenantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceQueue, isolatedTenantId, partition);
    }
}
