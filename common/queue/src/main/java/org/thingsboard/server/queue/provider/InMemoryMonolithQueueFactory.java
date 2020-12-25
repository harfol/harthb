package org.thingsboard.server.queue.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thingsboard.server.common.msg.queue.ServiceType;
import org.thingsboard.server.gen.js.JsInvokeProtos;
import org.thingsboard.server.gen.transport.TransportProtos;
import org.thingsboard.server.queue.TbQueueConsumer;
import org.thingsboard.server.queue.TbQueueProducer;
import org.thingsboard.server.queue.TbQueueRequestTemplate;
import org.thingsboard.server.queue.common.TbProtoJsQueueMsg;
import org.thingsboard.server.queue.common.TbProtoQueueMsg;
import org.thingsboard.server.queue.discovery.PartitionService;
import org.thingsboard.server.queue.discovery.TbServiceInfoProvider;
import org.thingsboard.server.queue.memory.InMemoryStorage;
import org.thingsboard.server.queue.memory.InMemoryTbQueueConsumer;
import org.thingsboard.server.queue.memory.InMemoryTbQueueProducer;
import org.thingsboard.server.queue.settings.TbQueueCoreSettings;
import org.thingsboard.server.queue.settings.TbQueueRuleEngineSettings;
import org.thingsboard.server.queue.settings.TbQueueTransportApiSettings;
import org.thingsboard.server.queue.settings.TbQueueTransportNotificationSettings;
import org.thingsboard.server.queue.settings.TbRuleEngineQueueConfiguration;

@Slf4j
@Component
@ConditionalOnExpression("'${queue.type:null}'=='in-memory' && '${service.type:null}'=='monolith'")
public class InMemoryMonolithQueueFactory implements TbCoreQueueFactory, TbRuleEngineQueueFactory {

    private final PartitionService partitionService;
    private final TbQueueCoreSettings coreSettings;
    private final TbServiceInfoProvider serviceInfoProvider;
    private final TbQueueRuleEngineSettings ruleEngineSettings;
    private final TbQueueTransportApiSettings transportApiSettings;
    private final TbQueueTransportNotificationSettings transportNotificationSettings;
    private final InMemoryStorage storage;

    public InMemoryMonolithQueueFactory(PartitionService partitionService, TbQueueCoreSettings coreSettings,
                                        TbQueueRuleEngineSettings ruleEngineSettings,
                                        TbServiceInfoProvider serviceInfoProvider,
                                        TbQueueTransportApiSettings transportApiSettings,
                                        TbQueueTransportNotificationSettings transportNotificationSettings) {
        this.partitionService = partitionService;
        this.coreSettings = coreSettings;
        this.serviceInfoProvider = serviceInfoProvider;
        this.ruleEngineSettings = ruleEngineSettings;
        this.transportApiSettings = transportApiSettings;
        this.transportNotificationSettings = transportNotificationSettings;
        this.storage = InMemoryStorage.getInstance();
    }

    @Override
    public TbQueueProducer<TbProtoQueueMsg<TransportProtos.ToTransportMsg>> createTransportNotificationsMsgProducer() {
        return new InMemoryTbQueueProducer<>(transportNotificationSettings.getNotificationsTopic());
    }

    @Override
    public TbQueueProducer<TbProtoQueueMsg<TransportProtos.ToRuleEngineMsg>> createRuleEngineMsgProducer() {
        return new InMemoryTbQueueProducer<>(ruleEngineSettings.getTopic());
    }

    @Override
    public TbQueueProducer<TbProtoQueueMsg<TransportProtos.ToRuleEngineNotificationMsg>> createRuleEngineNotificationsMsgProducer() {
        return new InMemoryTbQueueProducer<>(ruleEngineSettings.getTopic());
    }

    @Override
    public TbQueueProducer<TbProtoQueueMsg<TransportProtos.ToCoreMsg>> createTbCoreMsgProducer() {
        return new InMemoryTbQueueProducer<>(coreSettings.getTopic());
    }

    @Override
    public TbQueueProducer<TbProtoQueueMsg<TransportProtos.ToCoreNotificationMsg>> createTbCoreNotificationsMsgProducer() {
        return new InMemoryTbQueueProducer<>(coreSettings.getTopic());
    }

    @Override
    public TbQueueConsumer<TbProtoQueueMsg<TransportProtos.ToRuleEngineMsg>> createToRuleEngineMsgConsumer(TbRuleEngineQueueConfiguration configuration) {
        return new InMemoryTbQueueConsumer<>(ruleEngineSettings.getTopic());
    }

    @Override
    public TbQueueConsumer<TbProtoQueueMsg<TransportProtos.ToRuleEngineNotificationMsg>> createToRuleEngineNotificationsMsgConsumer() {
        return new InMemoryTbQueueConsumer<>(partitionService.getNotificationsTopic(ServiceType.TB_RULE_ENGINE, serviceInfoProvider.getServiceId()).getFullTopicName());
    }

    @Override
    public TbQueueConsumer<TbProtoQueueMsg<TransportProtos.ToCoreMsg>> createToCoreMsgConsumer() {
        return new InMemoryTbQueueConsumer<>(coreSettings.getTopic());
    }

    @Override
    public TbQueueConsumer<TbProtoQueueMsg<TransportProtos.ToCoreNotificationMsg>> createToCoreNotificationsMsgConsumer() {
        return new InMemoryTbQueueConsumer<>(partitionService.getNotificationsTopic(ServiceType.TB_CORE, serviceInfoProvider.getServiceId()).getFullTopicName());
    }

    @Override
    public TbQueueConsumer<TbProtoQueueMsg<TransportProtos.TransportApiRequestMsg>> createTransportApiRequestConsumer() {
        return new InMemoryTbQueueConsumer<>(transportApiSettings.getRequestsTopic());
    }

    @Override
    public TbQueueProducer<TbProtoQueueMsg<TransportProtos.TransportApiResponseMsg>> createTransportApiResponseProducer() {
        return new InMemoryTbQueueProducer<>(transportApiSettings.getResponsesTopic());
    }

    @Override
    public TbQueueRequestTemplate<TbProtoJsQueueMsg<JsInvokeProtos.RemoteJsRequest>, TbProtoQueueMsg<JsInvokeProtos.RemoteJsResponse>> createRemoteJsRequestTemplate() {
        return null;
    }

    @Override
    public TbQueueConsumer<TbProtoQueueMsg<TransportProtos.ToUsageStatsServiceMsg>> createToUsageStatsServiceMsgConsumer() {
        return new InMemoryTbQueueConsumer<>(coreSettings.getUsageStatsTopic());
    }

    @Override
    public TbQueueProducer<TbProtoQueueMsg<TransportProtos.ToUsageStatsServiceMsg>> createToUsageStatsServiceMsgProducer() {
        return new InMemoryTbQueueProducer<>(coreSettings.getUsageStatsTopic());
    }

    @Scheduled(fixedRateString = "${queue.in_memory.stats.print-interval-ms:60000}")
    private void printInMemoryStats() {
        storage.printStats();
    }


}
