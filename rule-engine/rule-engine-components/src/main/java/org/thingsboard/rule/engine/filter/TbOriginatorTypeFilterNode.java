package org.thingsboard.rule.engine.filter;

import lombok.extern.slf4j.Slf4j;
import org.thingsboard.rule.engine.api.util.TbNodeUtils;
import org.thingsboard.rule.engine.api.*;
import org.thingsboard.server.common.data.EntityType;
import org.thingsboard.server.common.data.plugin.ComponentType;
import org.thingsboard.server.common.msg.TbMsg;

@Slf4j
@RuleNode(
        type = ComponentType.FILTER,
        name = "originator type",
        configClazz = TbOriginatorTypeFilterNodeConfiguration.class,
        relationTypes = {"True", "False"},
        nodeDescription = "Filter incoming messages by message Originator Type",
        nodeDetails = "If Originator Type of incoming message is expected - send Message via <b>True</b> chain, otherwise <b>False</b> chain is used.",
        uiResources = {"static/rulenode/rulenode-core-config.js"},
        configDirective = "tbFilterNodeOriginatorTypeConfig")
public class TbOriginatorTypeFilterNode implements TbNode {

    TbOriginatorTypeFilterNodeConfiguration config;

    @Override
    public void init(TbContext ctx, TbNodeConfiguration configuration) throws TbNodeException {
        this.config = TbNodeUtils.convert(configuration, TbOriginatorTypeFilterNodeConfiguration.class);
    }

    @Override
    public void onMsg(TbContext ctx, TbMsg msg) {
        EntityType originatorType = msg.getOriginator().getEntityType();
        ctx.tellNext(msg, config.getOriginatorTypes().contains(originatorType) ? "True" : "False");
    }

    @Override
    public void destroy() {

    }
}
