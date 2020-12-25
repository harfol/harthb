package org.thingsboard.rule.engine.filter;

import lombok.extern.slf4j.Slf4j;
import org.thingsboard.common.util.ListeningExecutor;
import org.thingsboard.rule.engine.api.util.TbNodeUtils;
import org.thingsboard.rule.engine.api.*;
import org.thingsboard.server.common.data.plugin.ComponentType;
import org.thingsboard.server.common.msg.TbMsg;

import static org.thingsboard.common.util.DonAsynchron.withCallback;

@Slf4j
@RuleNode(
        type = ComponentType.FILTER,
        name = "script", relationTypes = {"True", "False"},
        configClazz = TbJsFilterNodeConfiguration.class,
        nodeDescription = "Filter incoming messages using JS script",
        nodeDetails = "Evaluate incoming Message with configured JS condition. " +
                "If <b>True</b> - send Message via <b>True</b> chain, otherwise <b>False</b> chain is used." +
                "Message payload can be accessed via <code>msg</code> property. For example <code>msg.temperature < 10;</code><br/>" +
                "Message metadata can be accessed via <code>metadata</code> property. For example <code>metadata.customerName === 'John';</code><br/>" +
                "Message type can be accessed via <code>msgType</code> property.",
        uiResources = {"static/rulenode/rulenode-core-config.js"},
        configDirective = "tbFilterNodeScriptConfig")

public class TbJsFilterNode implements TbNode {

    private TbJsFilterNodeConfiguration config;
    private ScriptEngine jsEngine;

    @Override
    public void init(TbContext ctx, TbNodeConfiguration configuration) throws TbNodeException {
        this.config = TbNodeUtils.convert(configuration, TbJsFilterNodeConfiguration.class);
        this.jsEngine = ctx.createJsScriptEngine(config.getJsScript());
    }

    @Override
    public void onMsg(TbContext ctx, TbMsg msg) {
        ctx.logJsEvalRequest();
        withCallback(jsEngine.executeFilterAsync(msg),
                filterResult -> {
                    ctx.logJsEvalResponse();
                    ctx.tellNext(msg, filterResult ? "True" : "False");
                },
                t -> {
                    ctx.tellFailure(msg, t);
                    ctx.logJsEvalFailure();
                }, ctx.getDbCallbackExecutor());
    }

    @Override
    public void destroy() {
        if (jsEngine != null) {
            jsEngine.destroy();
        }
    }
}
