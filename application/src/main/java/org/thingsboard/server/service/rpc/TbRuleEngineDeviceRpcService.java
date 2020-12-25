package org.thingsboard.server.service.rpc;

import org.thingsboard.rule.engine.api.RuleEngineRpcService;

/**
 * Created by ashvayka on 16.04.18.
 */
public interface TbRuleEngineDeviceRpcService extends RuleEngineRpcService {

    /**
     * Handles the RPC response from the Device Actor (Transport).
     *
     * @param response the RPC response
     */
    void processRpcResponseFromDevice(FromDeviceRpcResponse response);

}
