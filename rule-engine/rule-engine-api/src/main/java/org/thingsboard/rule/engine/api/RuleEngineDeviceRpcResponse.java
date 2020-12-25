package org.thingsboard.rule.engine.api;

import lombok.Builder;
import lombok.Data;
import org.thingsboard.server.common.data.id.DeviceId;

import java.util.Optional;

/**
 * Created by ashvayka on 02.04.18.
 */
@Data
@Builder
public final class RuleEngineDeviceRpcResponse {

    private final DeviceId deviceId;
    private final int requestId;
    private final Optional<String> response;
    private final Optional<RpcError> error;

}
