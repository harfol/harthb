package org.thingsboard.server.dao.sql.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.thingsboard.server.common.data.Dashboard;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.dao.dashboard.DashboardDao;
import org.thingsboard.server.dao.model.sql.DashboardEntity;
import org.thingsboard.server.dao.sql.JpaAbstractSearchTextDao;

import java.util.UUID;

/**
 * Created by Valerii Sosliuk on 5/6/2017.
 */
@Component
public class JpaDashboardDao extends JpaAbstractSearchTextDao<DashboardEntity, Dashboard> implements DashboardDao {

    @Autowired
    DashboardRepository dashboardRepository;

    @Override
    protected Class<DashboardEntity> getEntityClass() {
        return DashboardEntity.class;
    }

    @Override
    protected CrudRepository<DashboardEntity, UUID> getCrudRepository() {
        return dashboardRepository;
    }

    @Override
    public Long countByTenantId(TenantId tenantId) {
        return dashboardRepository.countByTenantId(tenantId.getId());
    }
}
