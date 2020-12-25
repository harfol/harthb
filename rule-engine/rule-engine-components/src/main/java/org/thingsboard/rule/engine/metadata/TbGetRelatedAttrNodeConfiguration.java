package org.thingsboard.rule.engine.metadata;

import lombok.Data;
import org.thingsboard.rule.engine.data.RelationsQuery;
import org.thingsboard.server.common.data.relation.EntityRelation;
import org.thingsboard.server.common.data.relation.EntitySearchDirection;
import org.thingsboard.server.common.data.relation.EntityTypeFilter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Data
public class TbGetRelatedAttrNodeConfiguration extends TbGetEntityAttrNodeConfiguration {

    private RelationsQuery relationsQuery;

    @Override
    public TbGetRelatedAttrNodeConfiguration defaultConfiguration() {
        TbGetRelatedAttrNodeConfiguration configuration = new TbGetRelatedAttrNodeConfiguration();
        Map<String, String> attrMapping = new HashMap<>();
        attrMapping.putIfAbsent("temperature", "tempo");
        configuration.setAttrMapping(attrMapping);
        configuration.setTelemetry(false);

        RelationsQuery relationsQuery = new RelationsQuery();
        relationsQuery.setDirection(EntitySearchDirection.FROM);
        relationsQuery.setMaxLevel(1);
        EntityTypeFilter entityTypeFilter = new EntityTypeFilter(EntityRelation.CONTAINS_TYPE, Collections.emptyList());
        relationsQuery.setFilters(Collections.singletonList(entityTypeFilter));
        configuration.setRelationsQuery(relationsQuery);

        return configuration;
    }
}
