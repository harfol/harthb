package org.thingsboard.server.common.data.query;

import lombok.Data;

import java.util.List;

@Data
public class DeviceSearchQueryFilter extends EntitySearchQueryFilter {

    @Override
    public EntityFilterType getType() {
        return EntityFilterType.DEVICE_SEARCH_QUERY;
    }

    private List<String> deviceTypes;

}
