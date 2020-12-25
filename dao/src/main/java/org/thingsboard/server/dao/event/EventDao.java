package org.thingsboard.server.dao.event;

import com.google.common.util.concurrent.ListenableFuture;
import org.thingsboard.server.common.data.Event;
import org.thingsboard.server.common.data.id.EntityId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.TimePageLink;
import org.thingsboard.server.dao.Dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The Interface EventDao.
 */
public interface EventDao extends Dao<Event> {

    /**
     * Save or update event object
     *
     * @param event the event object
     * @return saved event object
     */
    Event save(TenantId tenantId, Event event);

    /**
     * Save or update event object async
     *
     * @param event the event object
     * @return saved event object future
     */
    ListenableFuture<Event> saveAsync(Event event);

    /**
     * Save event object if it is not yet saved
     *
     * @param event the event object
     * @return saved event object
     */
    Optional<Event> saveIfNotExists(Event event);

    /**
     * Find event by tenantId, entityId and eventUid.
     *
     * @param tenantId the tenantId
     * @param entityId the entityId
     * @param eventType the eventType
     * @param eventUid the eventUid
     * @return the event
     */
    Event findEvent(UUID tenantId, EntityId entityId, String eventType, String eventUid);

    /**
     * Find events by tenantId, entityId and pageLink.
     *
     * @param tenantId the tenantId
     * @param entityId the entityId
     * @param pageLink the pageLink
     * @return the event list
     */
    PageData<Event> findEvents(UUID tenantId, EntityId entityId, TimePageLink pageLink);

    /**
     * Find events by tenantId, entityId, eventType and pageLink.
     *
     * @param tenantId the tenantId
     * @param entityId the entityId
     * @param eventType the eventType
     * @param pageLink the pageLink
     * @return the event list
     */
    PageData<Event> findEvents(UUID tenantId, EntityId entityId, String eventType, TimePageLink pageLink);

    /**
     * Find latest events by tenantId, entityId and eventType.
     *
     * @param tenantId the tenantId
     * @param entityId the entityId
     * @param eventType the eventType
     * @param limit the limit
     * @return the event list
     */
    List<Event> findLatestEvents(UUID tenantId, EntityId entityId, String eventType, int limit);

}
