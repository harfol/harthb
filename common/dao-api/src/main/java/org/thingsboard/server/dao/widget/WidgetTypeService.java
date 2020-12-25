package org.thingsboard.server.dao.widget;

import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.id.WidgetTypeId;
import org.thingsboard.server.common.data.widget.WidgetType;

import java.util.List;

public interface WidgetTypeService {

    WidgetType findWidgetTypeById(TenantId tenantId, WidgetTypeId widgetTypeId);

    WidgetType saveWidgetType(WidgetType widgetType);

    void deleteWidgetType(TenantId tenantId, WidgetTypeId widgetTypeId);

    List<WidgetType> findWidgetTypesByTenantIdAndBundleAlias(TenantId tenantId, String bundleAlias);

    WidgetType findWidgetTypeByTenantIdBundleAliasAndAlias(TenantId tenantId, String bundleAlias, String alias);

    void deleteWidgetTypesByTenantIdAndBundleAlias(TenantId tenantId, String bundleAlias);

}
