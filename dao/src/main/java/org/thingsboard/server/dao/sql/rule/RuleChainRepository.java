package org.thingsboard.server.dao.sql.rule;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.thingsboard.server.dao.model.sql.RuleChainEntity;

import java.util.UUID;

public interface RuleChainRepository extends PagingAndSortingRepository<RuleChainEntity, UUID> {

    @Query("SELECT rc FROM RuleChainEntity rc WHERE rc.tenantId = :tenantId " +
            "AND LOWER(rc.searchText) LIKE LOWER(CONCAT(:searchText, '%'))")
    Page<RuleChainEntity> findByTenantId(@Param("tenantId") UUID tenantId,
                                         @Param("searchText") String searchText,
                                         Pageable pageable);

    Long countByTenantId(UUID tenantId);
}
