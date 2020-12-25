package org.thingsboard.server.actors.ruleChain;

import lombok.Data;
import org.thingsboard.server.common.data.id.RuleNodeId;
import org.thingsboard.server.common.msg.MsgType;
import org.thingsboard.server.common.msg.TbActorMsg;
import org.thingsboard.server.common.msg.TbMsg;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by ashvayka on 19.03.18.
 */
@Data
class RuleNodeToRuleChainTellNextMsg implements TbActorMsg, Serializable {

    private static final long serialVersionUID = 4577026446412871820L;
    private final RuleNodeId originator;
    private final Set<String> relationTypes;
    private final TbMsg msg;
    private final String failureMessage;

    @Override
    public MsgType getMsgType() {
        return MsgType.RULE_TO_RULE_CHAIN_TELL_NEXT_MSG;
    }

}
