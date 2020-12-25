package org.thingsboard.server.dao.sql.usagerecord;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.thingsboard.server.dao.model.sql.ApiUsageStateEntity;

import java.util.UUID;

/**
 * @author Valerii Sosliuk
 */
public interface ApiUsageStateRepository extends CrudRepository<ApiUsageStateEntity, UUID> {

    @Query("SELECT ur FROM ApiUsageStateEntity ur WHERE ur.tenantId = :tenantId " +
            "AND ur.entityId = :tenantId AND ur.entityType = 'TENANT' ")
    ApiUsageStateEntity findByTenantId(@Param("tenantId") UUID tenantId);

    @Transactional
    @Modifying
    @Query("DELETE FROM ApiUsageStateEntity ur WHERE ur.tenantId = :tenantId")
    void deleteApiUsageStateByTenantId(@Param("tenantId") UUID tenantId);
}
