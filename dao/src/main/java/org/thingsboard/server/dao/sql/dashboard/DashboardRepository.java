package org.thingsboard.server.dao.sql.dashboard;

import org.springframework.data.repository.CrudRepository;
import org.thingsboard.server.dao.model.sql.DashboardEntity;

import java.util.UUID;

/**
 * Created by Valerii Sosliuk on 5/6/2017.
 */
public interface DashboardRepository extends CrudRepository<DashboardEntity, UUID> {

    Long countByTenantId(UUID tenantId);
}
