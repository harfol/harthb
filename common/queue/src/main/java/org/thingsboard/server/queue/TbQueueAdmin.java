package org.thingsboard.server.queue;

public interface TbQueueAdmin {

    void createTopicIfNotExists(String topic);

    void destroy();
}
