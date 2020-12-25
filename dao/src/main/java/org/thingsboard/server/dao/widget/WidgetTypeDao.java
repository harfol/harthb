package org.thingsboard.server.dao.widget;

import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.widget.WidgetType;
import org.thingsboard.server.dao.Dao;

import java.util.List;
import java.util.UUID;

/**
 * The Interface WidgetTypeDao.
 */
public interface WidgetTypeDao extends Dao<WidgetType> {

    /**
     * Save or update widget type object
     *
     * @param widgetType the widget type object
     * @return saved widget type object
     */
    WidgetType save(TenantId tenantId, WidgetType widgetType);

    /**
     * Find widget types by tenantId and bundleAlias.
     *
     * @param tenantId the tenantId
     * @param bundleAlias the bundle alias
     * @return the list of widget types objects
     */
    List<WidgetType> findWidgetTypesByTenantIdAndBundleAlias(UUID tenantId, String bundleAlias);

    /**
     * Find widget type by tenantId, bundleAlias and alias.
     *
     * @param tenantId the tenantId
     * @param bundleAlias the bundle alias
     * @param alias the alias
     * @return the widget type object
     */
    WidgetType findByTenantIdBundleAliasAndAlias(UUID tenantId, String bundleAlias, String alias);

}
