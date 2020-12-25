package org.thingsboard.server.dao.dashboard;

import org.thingsboard.server.common.data.DashboardInfo;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;
import org.thingsboard.server.dao.Dao;

import java.util.UUID;

/**
 * The Interface DashboardInfoDao.
 */
public interface DashboardInfoDao extends Dao<DashboardInfo> {

    /**
     * Find dashboards by tenantId and page link.
     *
     * @param tenantId the tenantId
     * @param pageLink the page link
     * @return the list of dashboard objects
     */
    PageData<DashboardInfo> findDashboardsByTenantId(UUID tenantId, PageLink pageLink);

    /**
     * Find dashboards by tenantId, customerId and page link.
     *
     * @param tenantId the tenantId
     * @param customerId the customerId
     * @param pageLink the page link
     * @return the list of dashboard objects
     */
    PageData<DashboardInfo> findDashboardsByTenantIdAndCustomerId(UUID tenantId, UUID customerId, PageLink pageLink);

}
