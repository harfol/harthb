package org.thingsboard.server.dao.sql.tenant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.thingsboard.server.dao.model.sql.TenantEntity;
import org.thingsboard.server.dao.model.sql.TenantInfoEntity;

import java.util.UUID;

/**
 * Created by Valerii Sosliuk on 4/30/2017.
 */
public interface TenantRepository extends PagingAndSortingRepository<TenantEntity, UUID> {

    @Query("SELECT new org.thingsboard.server.dao.model.sql.TenantInfoEntity(t, p.name) " +
            "FROM TenantEntity t " +
            "LEFT JOIN TenantProfileEntity p on p.id = t.tenantProfileId " +
            "WHERE t.id = :tenantId")
    TenantInfoEntity findTenantInfoById(@Param("tenantId") UUID tenantId);

    @Query("SELECT t FROM TenantEntity t WHERE t.region = :region " +
            "AND LOWER(t.searchText) LIKE LOWER(CONCAT(:textSearch, '%'))")
    Page<TenantEntity> findByRegionNextPage(@Param("region") String region,
                                            @Param("textSearch") String textSearch,
                                            Pageable pageable);

    @Query("SELECT new org.thingsboard.server.dao.model.sql.TenantInfoEntity(t, p.name) " +
            "FROM TenantEntity t " +
            "LEFT JOIN TenantProfileEntity p on p.id = t.tenantProfileId " +
            "WHERE t.region = :region " +
            "AND LOWER(t.searchText) LIKE LOWER(CONCAT(:textSearch, '%'))")
    Page<TenantInfoEntity> findTenantInfoByRegionNextPage(@Param("region") String region,
                                                          @Param("textSearch") String textSearch,
                                                          Pageable pageable);
}
