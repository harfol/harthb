package org.thingsboard.server.common.data.query;

import lombok.Data;
import org.thingsboard.server.common.data.id.EntityId;

@Data
public class ApiUsageStateFilter implements EntityFilter {
    @Override
    public EntityFilterType getType() {
        return EntityFilterType.API_USAGE_STATE;
    }

}
