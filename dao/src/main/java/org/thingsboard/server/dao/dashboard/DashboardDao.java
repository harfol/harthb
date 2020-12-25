package org.thingsboard.server.dao.dashboard;

import org.thingsboard.server.common.data.Dashboard;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.dao.Dao;
import org.thingsboard.server.dao.TenantEntityDao;

/**
 * The Interface DashboardDao.
 */
public interface DashboardDao extends Dao<Dashboard>, TenantEntityDao {

    /**
     * Save or update dashboard object
     *
     * @param dashboard the dashboard object
     * @return saved dashboard object
     */
    Dashboard save(TenantId tenantId, Dashboard dashboard);
}
