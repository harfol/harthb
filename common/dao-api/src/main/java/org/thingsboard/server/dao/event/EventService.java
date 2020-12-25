package org.thingsboard.server.dao.event;

import com.google.common.util.concurrent.ListenableFuture;
import org.thingsboard.server.common.data.Event;
import org.thingsboard.server.common.data.id.EntityId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.TimePageLink;

import java.util.List;
import java.util.Optional;

public interface EventService {

    Event save(Event event);

    ListenableFuture<Event> saveAsync(Event event);

    Optional<Event> saveIfNotExists(Event event);

    Optional<Event> findEvent(TenantId tenantId, EntityId entityId, String eventType, String eventUid);

    PageData<Event> findEvents(TenantId tenantId, EntityId entityId, TimePageLink pageLink);

    PageData<Event> findEvents(TenantId tenantId, EntityId entityId, String eventType, TimePageLink pageLink);

    List<Event> findLatestEvents(TenantId tenantId, EntityId entityId, String eventType, int limit);

    void removeEvents(TenantId tenantId, EntityId entityId);

}
