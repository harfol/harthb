package org.thingsboard.server.common.data.rpc;

import lombok.Data;

/**
 * @author Andrew Shvayka
 */
@Data
public class RpcRequest {
    private final String methodName;
    private final String requestData;
    private Long timeout;
}
