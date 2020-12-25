package org.thingsboard.server.service.queue;

import org.springframework.context.ApplicationListener;
import org.thingsboard.server.queue.discovery.PartitionChangeEvent;

public interface TbCoreConsumerService extends ApplicationListener<PartitionChangeEvent> {

}
