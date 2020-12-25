package org.thingsboard.server.common.data.query;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
public abstract class AbstractDataQuery<T extends EntityDataPageLink> extends EntityCountQuery {

    @Getter
    protected T pageLink;
    @Getter
    protected List<EntityKey> entityFields;
    @Getter
    protected List<EntityKey> latestValues;
    @Getter
    protected List<KeyFilter> keyFilters;

    public AbstractDataQuery() {
        super();
    }

    public AbstractDataQuery(EntityFilter entityFilter) {
        super(entityFilter);
    }

    public AbstractDataQuery(EntityFilter entityFilter,
                             T pageLink,
                             List<EntityKey> entityFields,
                             List<EntityKey> latestValues,
                             List<KeyFilter> keyFilters) {
        super(entityFilter);
        this.pageLink = pageLink;
        this.entityFields = entityFields;
        this.latestValues = latestValues;
        this.keyFilters = keyFilters;
    }

}
