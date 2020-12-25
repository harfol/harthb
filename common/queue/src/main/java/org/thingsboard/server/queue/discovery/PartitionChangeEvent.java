package org.thingsboard.server.queue.discovery;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.thingsboard.server.common.msg.queue.ServiceQueueKey;
import org.thingsboard.server.common.msg.queue.ServiceType;
import org.thingsboard.server.common.msg.queue.TopicPartitionInfo;

import java.util.Set;


public class PartitionChangeEvent extends ApplicationEvent {

    @Getter
    private final ServiceQueueKey serviceQueueKey;
    @Getter
    private final Set<TopicPartitionInfo> partitions;

    public PartitionChangeEvent(Object source, ServiceQueueKey serviceQueueKey, Set<TopicPartitionInfo> partitions) {
        super(source);
        this.serviceQueueKey = serviceQueueKey;
        this.partitions = partitions;
    }

    public ServiceType getServiceType() {
        return serviceQueueKey.getServiceQueue().getType();
    }
}
