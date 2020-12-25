package org.thingsboard.server.common.data.relation;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.thingsboard.server.common.data.EntityType;

import java.util.List;

/**
 * Created by ashvayka on 02.05.17.
 */
@Data
@AllArgsConstructor
public class EntityTypeFilter {

    private String relationType;

    private List<EntityType> entityTypes;
}
