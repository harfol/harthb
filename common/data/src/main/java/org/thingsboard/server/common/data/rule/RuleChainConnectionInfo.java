package org.thingsboard.server.common.data.rule;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import org.thingsboard.server.common.data.id.RuleChainId;

/**
 * Created by ashvayka on 21.03.18.
 */
@Data
public class RuleChainConnectionInfo {
    private int fromIndex;
    private RuleChainId targetRuleChainId;
    private JsonNode additionalInfo;
    private String type;
}
