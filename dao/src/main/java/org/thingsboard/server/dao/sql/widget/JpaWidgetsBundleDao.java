package org.thingsboard.server.dao.sql.widget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;
import org.thingsboard.server.common.data.widget.WidgetsBundle;
import org.thingsboard.server.dao.DaoUtil;
import org.thingsboard.server.dao.model.sql.WidgetsBundleEntity;
import org.thingsboard.server.dao.sql.JpaAbstractSearchTextDao;
import org.thingsboard.server.dao.widget.WidgetsBundleDao;

import java.util.Objects;
import java.util.UUID;

import static org.thingsboard.server.dao.model.ModelConstants.NULL_UUID;

/**
 * Created by Valerii Sosliuk on 4/23/2017.
 */
@Component
public class JpaWidgetsBundleDao extends JpaAbstractSearchTextDao<WidgetsBundleEntity, WidgetsBundle> implements WidgetsBundleDao {

    @Autowired
    private WidgetsBundleRepository widgetsBundleRepository;

    @Override
    protected Class<WidgetsBundleEntity> getEntityClass() {
        return WidgetsBundleEntity.class;
    }

    @Override
    protected CrudRepository<WidgetsBundleEntity, UUID> getCrudRepository() {
        return widgetsBundleRepository;
    }

    @Override
    public WidgetsBundle findWidgetsBundleByTenantIdAndAlias(UUID tenantId, String alias) {
        return DaoUtil.getData(widgetsBundleRepository.findWidgetsBundleByTenantIdAndAlias(tenantId, alias));
    }

    @Override
    public PageData<WidgetsBundle> findSystemWidgetsBundles(TenantId tenantId, PageLink pageLink) {
        return DaoUtil.toPageData(
                widgetsBundleRepository
                        .findSystemWidgetsBundles(
                                NULL_UUID,
                                Objects.toString(pageLink.getTextSearch(), ""),
                                DaoUtil.toPageable(pageLink)));
    }

    @Override
    public PageData<WidgetsBundle> findTenantWidgetsBundlesByTenantId(UUID tenantId, PageLink pageLink) {
        return DaoUtil.toPageData(
                widgetsBundleRepository
                        .findTenantWidgetsBundlesByTenantId(
                                tenantId,
                                Objects.toString(pageLink.getTextSearch(), ""),
                                DaoUtil.toPageable(pageLink)));
    }

    @Override
    public PageData<WidgetsBundle> findAllTenantWidgetsBundlesByTenantId(UUID tenantId, PageLink pageLink) {
        return DaoUtil.toPageData(
                widgetsBundleRepository
                        .findAllTenantWidgetsBundlesByTenantId(
                                tenantId,
                                NULL_UUID,
                                Objects.toString(pageLink.getTextSearch(), ""),
                                DaoUtil.toPageable(pageLink)));
    }
}
