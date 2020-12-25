package org.thingsboard.server.dao.sql.tenant;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.thingsboard.server.common.data.Tenant;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;
import org.thingsboard.server.dao.AbstractJpaDaoTest;
import org.thingsboard.server.dao.service.AbstractServiceTest;
import org.thingsboard.server.dao.tenant.TenantDao;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Valerii Sosliuk on 4/30/2017.
 */
public class JpaTenantDaoTest extends AbstractJpaDaoTest {

    @Autowired
    private TenantDao tenantDao;

    @Test
    @DatabaseSetup("classpath:dbunit/empty_dataset.xml")
    public void testFindTenantsByRegion() {
        createTenants();
        assertEquals(60, tenantDao.find(AbstractServiceTest.SYSTEM_TENANT_ID).size());

        PageLink pageLink = new PageLink(20, 0, "title");
        PageData<Tenant> tenants1 = tenantDao.findTenantsByRegion(AbstractServiceTest.SYSTEM_TENANT_ID, "REGION_1", pageLink);
        assertEquals(20, tenants1.getData().size());

        pageLink = pageLink.nextPageLink();
        PageData<Tenant> tenants2 = tenantDao.findTenantsByRegion(AbstractServiceTest.SYSTEM_TENANT_ID,"REGION_1",
                pageLink);
        assertEquals(10, tenants2.getData().size());

        pageLink = pageLink.nextPageLink();
        PageData<Tenant> tenants3 = tenantDao.findTenantsByRegion(AbstractServiceTest.SYSTEM_TENANT_ID,"REGION_1",
                pageLink);
        assertEquals(0, tenants3.getData().size());
    }

    private void createTenants() {
        for (int i = 0; i < 30; i++) {
            createTenant("REGION_1", "TITLE", i);
            createTenant("REGION_2", "TITLE", i);
        }
    }

    private void createTenant(String region, String title, int index) {
        Tenant tenant = new Tenant();
        tenant.setId(new TenantId(Uuids.timeBased()));
        tenant.setRegion(region);
        tenant.setTitle(title + "_" + index);
        tenantDao.save(AbstractServiceTest.SYSTEM_TENANT_ID, tenant);
    }

}
