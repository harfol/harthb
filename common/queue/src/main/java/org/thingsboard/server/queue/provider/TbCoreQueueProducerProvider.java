package org.thingsboard.server.queue.provider;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;
import org.thingsboard.server.gen.transport.TransportProtos.ToCoreMsg;
import org.thingsboard.server.gen.transport.TransportProtos.ToCoreNotificationMsg;
import org.thingsboard.server.gen.transport.TransportProtos.ToRuleEngineMsg;
import org.thingsboard.server.gen.transport.TransportProtos.ToRuleEngineNotificationMsg;
import org.thingsboard.server.gen.transport.TransportProtos.ToTransportMsg;
import org.thingsboard.server.gen.transport.TransportProtos.ToUsageStatsServiceMsg;
import org.thingsboard.server.queue.TbQueueProducer;
import org.thingsboard.server.queue.common.TbProtoQueueMsg;

import javax.annotation.PostConstruct;

@Service
@ConditionalOnExpression("'${service.type:null}'=='monolith' || '${service.type:null}'=='tb-core'")
public class TbCoreQueueProducerProvider implements TbQueueProducerProvider {

    private final TbCoreQueueFactory tbQueueProvider;
    private TbQueueProducer<TbProtoQueueMsg<ToTransportMsg>> toTransport;
    private TbQueueProducer<TbProtoQueueMsg<ToRuleEngineMsg>> toRuleEngine;
    private TbQueueProducer<TbProtoQueueMsg<ToCoreMsg>> toTbCore;
    private TbQueueProducer<TbProtoQueueMsg<ToRuleEngineNotificationMsg>> toRuleEngineNotifications;
    private TbQueueProducer<TbProtoQueueMsg<ToCoreNotificationMsg>> toTbCoreNotifications;
    private TbQueueProducer<TbProtoQueueMsg<ToUsageStatsServiceMsg>> toUsageStats;

    public TbCoreQueueProducerProvider(TbCoreQueueFactory tbQueueProvider) {
        this.tbQueueProvider = tbQueueProvider;
    }

    @PostConstruct
    public void init() {
        this.toTbCore = tbQueueProvider.createTbCoreMsgProducer();
        this.toTransport = tbQueueProvider.createTransportNotificationsMsgProducer();
        this.toRuleEngine = tbQueueProvider.createRuleEngineMsgProducer();
        this.toRuleEngineNotifications = tbQueueProvider.createRuleEngineNotificationsMsgProducer();
        this.toTbCoreNotifications = tbQueueProvider.createTbCoreNotificationsMsgProducer();
        this.toUsageStats = tbQueueProvider.createToUsageStatsServiceMsgProducer();
    }

    @Override
    public TbQueueProducer<TbProtoQueueMsg<ToTransportMsg>> getTransportNotificationsMsgProducer() {
        return toTransport;
    }

    @Override
    public TbQueueProducer<TbProtoQueueMsg<ToRuleEngineMsg>> getRuleEngineMsgProducer() {
        return toRuleEngine;
    }

    @Override
    public TbQueueProducer<TbProtoQueueMsg<ToRuleEngineNotificationMsg>> getRuleEngineNotificationsMsgProducer() {
        return toRuleEngineNotifications;
    }

    @Override
    public TbQueueProducer<TbProtoQueueMsg<ToCoreMsg>> getTbCoreMsgProducer() {
        return toTbCore;
    }

    @Override
    public TbQueueProducer<TbProtoQueueMsg<ToCoreNotificationMsg>> getTbCoreNotificationsMsgProducer() {
        return toTbCoreNotifications;
    }

    @Override
    public TbQueueProducer<TbProtoQueueMsg<ToUsageStatsServiceMsg>> getTbUsageStatsMsgProducer() {
        return toUsageStats;
    }
}
