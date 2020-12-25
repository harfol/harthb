package org.thingsboard.rule.engine.filter;

import lombok.extern.slf4j.Slf4j;
import org.thingsboard.common.util.ListeningExecutor;
import org.thingsboard.rule.engine.api.util.TbNodeUtils;
import org.thingsboard.rule.engine.api.*;
import org.thingsboard.server.common.data.plugin.ComponentType;
import org.thingsboard.server.common.msg.TbMsg;

import java.util.Set;

import static org.thingsboard.common.util.DonAsynchron.withCallback;

@Slf4j
@RuleNode(
        type = ComponentType.FILTER,
        name = "switch", customRelations = true,
        relationTypes = {},
        configClazz = TbJsSwitchNodeConfiguration.class,
        nodeDescription = "Route incoming Message to one or multiple output chains",
        nodeDetails = "Node executes configured JS script. Script should return array of next Chain names where Message should be routed. " +
                "If Array is empty - message not routed to next Node. " +
                "Message payload can be accessed via <code>msg</code> property. For example <code>msg.temperature < 10;</code><br/>" +
                "Message metadata can be accessed via <code>metadata</code> property. For example <code>metadata.customerName === 'John';</code><br/>" +
                "Message type can be accessed via <code>msgType</code> property.",
        uiResources = {"static/rulenode/rulenode-core-config.js"},
        configDirective = "tbFilterNodeSwitchConfig")
public class TbJsSwitchNode implements TbNode {

    private TbJsSwitchNodeConfiguration config;
    private ScriptEngine jsEngine;

    @Override
    public void init(TbContext ctx, TbNodeConfiguration configuration) throws TbNodeException {
        this.config = TbNodeUtils.convert(configuration, TbJsSwitchNodeConfiguration.class);
        this.jsEngine = ctx.createJsScriptEngine(config.getJsScript());
    }

    @Override
    public void onMsg(TbContext ctx, TbMsg msg) {
        ListeningExecutor jsExecutor = ctx.getJsExecutor();
        ctx.logJsEvalRequest();
        withCallback(jsExecutor.executeAsync(() -> jsEngine.executeSwitch(msg)),
                result -> {
                    ctx.logJsEvalResponse();
                    processSwitch(ctx, msg, result);
                },
                t -> {
                    ctx.logJsEvalFailure();
                    ctx.tellFailure(msg, t);
                }, ctx.getDbCallbackExecutor());
    }

    private void processSwitch(TbContext ctx, TbMsg msg, Set<String> nextRelations) {
        ctx.tellNext(msg, nextRelations);
    }

    @Override
    public void destroy() {
        if (jsEngine != null) {
            jsEngine.destroy();
        }
    }
}
