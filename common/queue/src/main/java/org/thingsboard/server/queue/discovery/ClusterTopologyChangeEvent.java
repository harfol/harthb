package org.thingsboard.server.queue.discovery;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.thingsboard.server.common.msg.queue.ServiceQueueKey;

import java.util.Set;


public class ClusterTopologyChangeEvent extends ApplicationEvent {

    @Getter
    private final Set<ServiceQueueKey> serviceQueueKeys;

    public ClusterTopologyChangeEvent(Object source, Set<ServiceQueueKey> serviceQueueKeys) {
        super(source);
        this.serviceQueueKeys = serviceQueueKeys;
    }
}
