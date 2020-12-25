package org.thingsboard.server.service.queue.processing;

import org.thingsboard.server.gen.transport.TransportProtos.ToRuleEngineMsg;
import org.thingsboard.server.queue.common.TbProtoQueueMsg;

import java.util.UUID;

public class IdMsgPair {
    final UUID uuid;
    final TbProtoQueueMsg<ToRuleEngineMsg> msg;

    public IdMsgPair(UUID uuid, TbProtoQueueMsg<ToRuleEngineMsg> msg) {
        this.uuid = uuid;
        this.msg = msg;
    }
}
