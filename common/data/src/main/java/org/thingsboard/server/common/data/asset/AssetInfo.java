package org.thingsboard.server.common.data.asset;

import lombok.Data;
import org.thingsboard.server.common.data.id.AssetId;

@Data
public class AssetInfo extends Asset {

    private String customerTitle;
    private boolean customerIsPublic;

    public AssetInfo() {
        super();
    }

    public AssetInfo(AssetId assetId) {
        super(assetId);
    }

    public AssetInfo(Asset asset, String customerTitle, boolean customerIsPublic) {
        super(asset);
        this.customerTitle = customerTitle;
        this.customerIsPublic = customerIsPublic;
    }
}
