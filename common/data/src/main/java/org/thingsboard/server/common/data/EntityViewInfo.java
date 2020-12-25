package org.thingsboard.server.common.data;

import lombok.Data;
import org.thingsboard.server.common.data.id.EntityViewId;

@Data
public class EntityViewInfo extends EntityView {

    private String customerTitle;
    private boolean customerIsPublic;

    public EntityViewInfo() {
        super();
    }

    public EntityViewInfo(EntityViewId entityViewId) {
        super(entityViewId);
    }

    public EntityViewInfo(EntityView entityView, String customerTitle, boolean customerIsPublic) {
        super(entityView);
        this.customerTitle = customerTitle;
        this.customerIsPublic = customerIsPublic;
    }
}
