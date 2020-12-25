package org.thingsboard.server.dao.sql.settings;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.thingsboard.server.common.data.AdminSettings;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.dao.DaoUtil;
import org.thingsboard.server.dao.model.sql.AdminSettingsEntity;
import org.thingsboard.server.dao.settings.AdminSettingsDao;
import org.thingsboard.server.dao.sql.JpaAbstractDao;

import java.util.UUID;

@Component
@Slf4j
public class JpaAdminSettingsDao extends JpaAbstractDao<AdminSettingsEntity, AdminSettings> implements AdminSettingsDao {

    @Autowired
    private AdminSettingsRepository adminSettingsRepository;

    @Override
    protected Class<AdminSettingsEntity> getEntityClass() {
        return AdminSettingsEntity.class;
    }

    @Override
    protected CrudRepository<AdminSettingsEntity, UUID> getCrudRepository() {
        return adminSettingsRepository;
    }

    @Override
    public AdminSettings findByKey(TenantId tenantId, String key) {
        return DaoUtil.getData(adminSettingsRepository.findByKey(key));
    }
}
