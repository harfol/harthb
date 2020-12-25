package org.thingsboard.server.queue.kafka;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.thingsboard.server.queue.TbQueueAdmin;
import org.thingsboard.server.queue.TbQueueMsg;
import org.thingsboard.server.queue.common.AbstractTbQueueConsumerTemplate;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * Created by ashvayka on 24.09.18.
 */
@Slf4j
public class TbKafkaConsumerTemplate<T extends TbQueueMsg> extends AbstractTbQueueConsumerTemplate<ConsumerRecord<String, byte[]>, T> {

    private final TbQueueAdmin admin;
    private final KafkaConsumer<String, byte[]> consumer;
    private final TbKafkaDecoder<T> decoder;

    @Builder
    private TbKafkaConsumerTemplate(TbKafkaSettings settings, TbKafkaDecoder<T> decoder,
                                    String clientId, String groupId, String topic,
                                    TbQueueAdmin admin) {
        super(topic);
        Properties props = settings.toConsumerProps();
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, clientId);
        if (groupId != null) {
            props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        }

        this.admin = admin;
        this.consumer = new KafkaConsumer<>(props);
        this.decoder = decoder;
    }

    @Override
    protected void doSubscribe(List<String> topicNames) {
        if (!topicNames.isEmpty()) {
            topicNames.forEach(admin::createTopicIfNotExists);
            consumer.subscribe(topicNames);
        } else {
            consumer.unsubscribe();
        }
    }

    @Override
    protected List<ConsumerRecord<String, byte[]>> doPoll(long durationInMillis) {
        ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMillis(durationInMillis));
        if (records.isEmpty()) {
            return Collections.emptyList();
        } else {
            List<ConsumerRecord<String, byte[]>> recordList = new ArrayList<>(256);
            records.forEach(recordList::add);
            return recordList;
        }
    }

    @Override
    public T decode(ConsumerRecord<String, byte[]> record) throws IOException {
        return decoder.decode(new KafkaTbQueueMsg(record));
    }

    @Override
    protected void doCommit() {
        consumer.commitAsync();
    }

    @Override
    protected void doUnsubscribe() {
        if (consumer != null) {
            consumer.unsubscribe();
            consumer.close();
        }
    }

}
