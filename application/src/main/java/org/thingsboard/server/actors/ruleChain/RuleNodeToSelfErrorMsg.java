package org.thingsboard.server.actors.ruleChain;

import lombok.Data;
import org.thingsboard.server.common.msg.MsgType;
import org.thingsboard.server.common.msg.TbActorMsg;
import org.thingsboard.server.common.msg.TbMsg;

/**
 * Created by ashvayka on 19.03.18.
 */
@Data
final class RuleNodeToSelfErrorMsg implements TbActorMsg {

    private final TbMsg msg;
    private final Throwable error;

    @Override
    public MsgType getMsgType() {
        return MsgType.RULE_TO_SELF_ERROR_MSG;
    }

}
