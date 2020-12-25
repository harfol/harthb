package org.thingsboard.server.dao.sql.dashboard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.thingsboard.server.dao.model.sql.DashboardInfoEntity;

import java.util.UUID;

/**
 * Created by Valerii Sosliuk on 5/6/2017.
 */
public interface DashboardInfoRepository extends PagingAndSortingRepository<DashboardInfoEntity, UUID> {

    @Query("SELECT di FROM DashboardInfoEntity di WHERE di.tenantId = :tenantId " +
            "AND LOWER(di.searchText) LIKE LOWER(CONCAT(:searchText, '%'))")
    Page<DashboardInfoEntity> findByTenantId(@Param("tenantId") UUID tenantId,
                                             @Param("searchText") String searchText,
                                             Pageable pageable);

    @Query("SELECT di FROM DashboardInfoEntity di, RelationEntity re WHERE di.tenantId = :tenantId " +
            "AND di.id = re.toId AND re.toType = 'DASHBOARD' AND re.relationTypeGroup = 'DASHBOARD' " +
            "AND re.relationType = 'Contains' AND re.fromId = :customerId AND re.fromType = 'CUSTOMER' " +
            "AND LOWER(di.searchText) LIKE LOWER(CONCAT(:searchText, '%'))")
    Page<DashboardInfoEntity> findByTenantIdAndCustomerId(@Param("tenantId") UUID tenantId,
                                                          @Param("customerId") UUID customerId,
                                                          @Param("searchText") String searchText,
                                                          Pageable pageable);

}
