package org.thingsboard.server.actors.ruleChain;

import lombok.Data;
import org.thingsboard.server.common.data.id.RuleChainId;
import org.thingsboard.server.common.msg.MsgType;
import org.thingsboard.server.common.msg.TbActorMsg;
import org.thingsboard.server.common.msg.TbMsg;
import org.thingsboard.server.common.msg.aware.RuleChainAwareMsg;

/**
 * Created by ashvayka on 19.03.18.
 */
@Data
public final class RuleChainToRuleChainMsg implements TbActorMsg, RuleChainAwareMsg {

    private final RuleChainId target;
    private final RuleChainId source;
    private final TbMsg msg;
    private final String fromRelationType;

    @Override
    public RuleChainId getRuleChainId() {
        return target;
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.RULE_CHAIN_TO_RULE_CHAIN_MSG;
    }
}
