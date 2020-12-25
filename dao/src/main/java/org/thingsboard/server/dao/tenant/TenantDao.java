package org.thingsboard.server.dao.tenant;

import org.thingsboard.server.common.data.Tenant;
import org.thingsboard.server.common.data.TenantInfo;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;
import org.thingsboard.server.dao.Dao;

import java.util.UUID;

public interface TenantDao extends Dao<Tenant> {

    TenantInfo findTenantInfoById(TenantId tenantId, UUID id);

    /**
     * Save or update tenant object
     *
     * @param tenant the tenant object
     * @return saved tenant object
     */
    Tenant save(TenantId tenantId, Tenant tenant);
    
    /**
     * Find tenants by region and page link.
     * 
     * @param region the region
     * @param pageLink the page link
     * @return the list of tenant objects
     */
    PageData<Tenant> findTenantsByRegion(TenantId tenantId, String region, PageLink pageLink);

    PageData<TenantInfo> findTenantInfosByRegion(TenantId tenantId, String region, PageLink pageLink);
    
}
