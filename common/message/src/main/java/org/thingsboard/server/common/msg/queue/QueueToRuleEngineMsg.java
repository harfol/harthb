package org.thingsboard.server.common.msg.queue;

import lombok.Data;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.msg.MsgType;
import org.thingsboard.server.common.msg.TbActorMsg;
import org.thingsboard.server.common.msg.TbMsg;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by ashvayka on 15.03.18.
 */
@Data
public final class QueueToRuleEngineMsg implements TbActorMsg {

    private final TenantId tenantId;
    private final TbMsg tbMsg;
    private final Set<String> relationTypes;
    private final String failureMessage;

    @Override
    public MsgType getMsgType() {
        return MsgType.QUEUE_TO_RULE_ENGINE_MSG;
    }

    public boolean isTellNext() {
        return relationTypes != null && !relationTypes.isEmpty();
    }
}
