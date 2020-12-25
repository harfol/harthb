package org.thingsboard.server.common.data.rule;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.thingsboard.server.common.data.id.RuleChainId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.plugin.ComponentLifecycleEvent;

@Data
@AllArgsConstructor
public class RuleChainImportResult {

    private TenantId tenantId;
    private RuleChainId ruleChainId;
    private ComponentLifecycleEvent lifecycleEvent;
}
