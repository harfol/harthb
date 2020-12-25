package org.thingsboard.server.dao.tenant;

import com.google.common.util.concurrent.ListenableFuture;
import org.thingsboard.server.common.data.Tenant;
import org.thingsboard.server.common.data.TenantInfo;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;

public interface TenantService {

    Tenant findTenantById(TenantId tenantId);

    TenantInfo findTenantInfoById(TenantId tenantId);

    ListenableFuture<Tenant> findTenantByIdAsync(TenantId callerId, TenantId tenantId);
    
    Tenant saveTenant(Tenant tenant);
    
    void deleteTenant(TenantId tenantId);
    
    PageData<Tenant> findTenants(PageLink pageLink);

    PageData<TenantInfo> findTenantInfos(PageLink pageLink);
    
    void deleteTenants();
}
