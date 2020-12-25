package org.thingsboard.server.common.data;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import org.thingsboard.server.common.data.id.EntityId;
import org.thingsboard.server.common.data.id.EventId;
import org.thingsboard.server.common.data.id.TenantId;

/**
 * @author Andrew Shvayka
 */
@Data
public class Event extends BaseData<EventId> {

    private TenantId tenantId;
    private String type;
    private String uid;
    private EntityId entityId;
    private transient JsonNode body;

    public Event() {
        super();
    }

    public Event(EventId id) {
        super(id);
    }

    public Event(Event event) {
        super(event);
    }

}
