package org.thingsboard.server.dao.rule;

import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;
import org.thingsboard.server.common.data.rule.RuleChain;
import org.thingsboard.server.dao.Dao;
import org.thingsboard.server.dao.TenantEntityDao;

import java.util.UUID;

/**
 * Created by igor on 3/12/18.
 */
public interface RuleChainDao extends Dao<RuleChain>, TenantEntityDao {

    /**
     * Find rule chains by tenantId and page link.
     *
     * @param tenantId the tenantId
     * @param pageLink the page link
     * @return the list of rule chain objects
     */
    PageData<RuleChain> findRuleChainsByTenantId(UUID tenantId, PageLink pageLink);
}
