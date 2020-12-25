package org.thingsboard.server.dao.sql.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.thingsboard.server.common.data.EntityType;
import org.thingsboard.server.dao.model.sql.EventEntity;

import java.util.List;
import java.util.UUID;

/**
 * Created by Valerii Sosliuk on 5/3/2017.
 */
public interface EventRepository extends PagingAndSortingRepository<EventEntity, UUID> {

    EventEntity findByTenantIdAndEntityTypeAndEntityIdAndEventTypeAndEventUid(UUID tenantId,
                                                                              EntityType entityType,
                                                                              UUID entityId,
                                                                              String eventType,
                                                                              String eventUid);

    EventEntity findByTenantIdAndEntityTypeAndEntityId(UUID tenantId,
                                                       EntityType entityType,
                                                       UUID entityId);

    @Query("SELECT e FROM EventEntity e WHERE e.tenantId = :tenantId AND e.entityType = :entityType " +
            "AND e.entityId = :entityId AND e.eventType = :eventType ORDER BY e.eventType DESC, e.id DESC")
    List<EventEntity> findLatestByTenantIdAndEntityTypeAndEntityIdAndEventType(
                                                    @Param("tenantId") UUID tenantId,
                                                    @Param("entityType") EntityType entityType,
                                                    @Param("entityId") UUID entityId,
                                                    @Param("eventType") String eventType,
                                                    Pageable pageable);

    @Query("SELECT e FROM EventEntity e WHERE " +
            "e.tenantId = :tenantId " +
            "AND e.entityType = :entityType AND e.entityId = :entityId " +
            "AND (:startTime IS NULL OR e.createdTime >= :startTime) " +
            "AND (:endTime IS NULL OR e.createdTime <= :endTime) " +
            "AND LOWER(e.eventType) LIKE LOWER(CONCAT(:textSearch, '%'))"
    )
    Page<EventEntity> findEventsByTenantIdAndEntityId(@Param("tenantId") UUID tenantId,
                                                      @Param("entityType") EntityType entityType,
                                                      @Param("entityId") UUID entityId,
                                                      @Param("textSearch") String textSearch,
                                                      @Param("startTime") Long startTime,
                                                      @Param("endTime") Long endTime,
                                                      Pageable pageable);

    @Query("SELECT e FROM EventEntity e WHERE " +
            "e.tenantId = :tenantId " +
            "AND e.entityType = :entityType AND e.entityId = :entityId " +
            "AND e.eventType = :eventType " +
            "AND (:startTime IS NULL OR e.createdTime >= :startTime) " +
            "AND (:endTime IS NULL OR e.createdTime <= :endTime)"
    )
    Page<EventEntity> findEventsByTenantIdAndEntityIdAndEventType(@Param("tenantId") UUID tenantId,
                                                                  @Param("entityType") EntityType entityType,
                                                                  @Param("entityId") UUID entityId,
                                                                  @Param("eventType") String eventType,
                                                                  @Param("startTime") Long startTime,
                                                                  @Param("endTime") Long endTime,
                                                                  Pageable pageable);

}
