package org.thingsboard.server.common.data.query;

import lombok.Data;

import java.util.List;

@Data
public class EntityViewSearchQueryFilter extends EntitySearchQueryFilter {

    @Override
    public EntityFilterType getType() {
        return EntityFilterType.ENTITY_VIEW_SEARCH_QUERY;
    }

    private List<String> entityViewTypes;

}
