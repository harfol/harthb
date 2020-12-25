package org.thingsboard.server.common.data.rule;

import lombok.Data;

/**
 * Created by ashvayka on 21.03.18.
 */
@Data
public class NodeConnectionInfo {
    private int fromIndex;
    private int toIndex;
    private String type;
}
