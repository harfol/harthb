package org.thingsboard.server.common.data.entityview;

import lombok.Data;
import org.thingsboard.server.common.data.EntityType;
import org.thingsboard.server.common.data.relation.EntityRelation;
import org.thingsboard.server.common.data.relation.EntityRelationsQuery;
import org.thingsboard.server.common.data.relation.EntityTypeFilter;
import org.thingsboard.server.common.data.relation.RelationsSearchParameters;

import java.util.Collections;
import java.util.List;

@Data
public class EntityViewSearchQuery {

    private RelationsSearchParameters parameters;
    private String relationType;
    private List<String> entityViewTypes;

    public EntityRelationsQuery toEntitySearchQuery() {
        EntityRelationsQuery query = new EntityRelationsQuery();
        query.setParameters(parameters);
        query.setFilters(
                Collections.singletonList(new EntityTypeFilter(relationType == null ? EntityRelation.CONTAINS_TYPE : relationType,
                        Collections.singletonList(EntityType.ENTITY_VIEW))));
        return query;
    }
}
