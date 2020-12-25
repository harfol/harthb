package org.thingsboard.server.common.data.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
public class EntityDataQuery extends AbstractDataQuery<EntityDataPageLink> {

    public EntityDataQuery() {
    }

    public EntityDataQuery(EntityFilter entityFilter) {
        super(entityFilter);
    }

    public EntityDataQuery(EntityFilter entityFilter, EntityDataPageLink pageLink, List<EntityKey> entityFields, List<EntityKey> latestValues, List<KeyFilter> keyFilters) {
        super(entityFilter, pageLink, entityFields, latestValues, keyFilters);
    }

    @JsonIgnore
    public EntityDataQuery next() {
        return new EntityDataQuery(getEntityFilter(), getPageLink().nextPageLink(), entityFields, latestValues, keyFilters);
    }

}
