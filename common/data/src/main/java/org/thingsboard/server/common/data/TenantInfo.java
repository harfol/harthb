package org.thingsboard.server.common.data;

import lombok.Data;
import org.thingsboard.server.common.data.id.TenantId;

@Data
public class TenantInfo extends Tenant {

    private String tenantProfileName;

    public TenantInfo() {
        super();
    }

    public TenantInfo(TenantId tenantId) {
        super(tenantId);
    }

    public TenantInfo(Tenant tenant, String tenantProfileName) {
        super(tenant);
        this.tenantProfileName = tenantProfileName;
    }

}
