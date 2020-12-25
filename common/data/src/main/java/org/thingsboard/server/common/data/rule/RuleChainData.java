package org.thingsboard.server.common.data.rule;

import lombok.Data;

import java.util.List;

@Data
public class RuleChainData {

    List<RuleChain> ruleChains;
    List<RuleChainMetaData> metadata;
}
