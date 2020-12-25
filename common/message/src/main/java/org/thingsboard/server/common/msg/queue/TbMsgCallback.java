package org.thingsboard.server.common.msg.queue;

import org.thingsboard.server.common.data.id.RuleNodeId;

public interface TbMsgCallback {

    TbMsgCallback EMPTY = new TbMsgCallback() {

        @Override
        public void onSuccess() {

        }

        @Override
        public void onFailure(RuleEngineException e) {

        }
    };

    void onSuccess();

    void onFailure(RuleEngineException e);

    default void onProcessingStart(RuleNodeInfo ruleNodeInfo) {
    }

    default void onProcessingEnd(RuleNodeId ruleNodeId) {
    }


}
