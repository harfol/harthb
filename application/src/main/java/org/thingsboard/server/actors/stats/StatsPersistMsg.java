package org.thingsboard.server.actors.stats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.thingsboard.server.common.data.id.EntityId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.msg.MsgType;
import org.thingsboard.server.common.msg.TbActorMsg;

@AllArgsConstructor
@Getter
@ToString
public final class StatsPersistMsg implements TbActorMsg {

    private long messagesProcessed;
    private long errorsOccurred;
    private TenantId tenantId;
    private EntityId entityId;

    @Override
    public MsgType getMsgType() {
        return MsgType.STATS_PERSIST_MSG;
    }
}
