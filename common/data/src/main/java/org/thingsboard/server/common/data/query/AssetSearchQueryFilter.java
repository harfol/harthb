package org.thingsboard.server.common.data.query;

import lombok.Data;

import java.util.List;

@Data
public class AssetSearchQueryFilter extends EntitySearchQueryFilter {

    @Override
    public EntityFilterType getType() {
        return EntityFilterType.ASSET_SEARCH_QUERY;
    }

    private List<String> assetTypes;

}
