package org.thingsboard.server.dao.model.sql;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.thingsboard.server.common.data.AdminSettings;
import org.thingsboard.server.common.data.id.AdminSettingsId;
import org.thingsboard.server.dao.model.BaseEntity;
import org.thingsboard.server.dao.model.BaseSqlEntity;
import org.thingsboard.server.dao.util.mapping.JsonStringType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static org.thingsboard.server.dao.model.ModelConstants.ADMIN_SETTINGS_COLUMN_FAMILY_NAME;
import static org.thingsboard.server.dao.model.ModelConstants.ADMIN_SETTINGS_JSON_VALUE_PROPERTY;
import static org.thingsboard.server.dao.model.ModelConstants.ADMIN_SETTINGS_KEY_PROPERTY;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@TypeDef(name = "json", typeClass = JsonStringType.class)
@Table(name = ADMIN_SETTINGS_COLUMN_FAMILY_NAME)
public final class AdminSettingsEntity extends BaseSqlEntity<AdminSettings> implements BaseEntity<AdminSettings> {

    @Column(name = ADMIN_SETTINGS_KEY_PROPERTY)
    private String key;

    @Type(type = "json")
    @Column(name = ADMIN_SETTINGS_JSON_VALUE_PROPERTY)
    private JsonNode jsonValue;

    public AdminSettingsEntity() {
        super();
    }

    public AdminSettingsEntity(AdminSettings adminSettings) {
        if (adminSettings.getId() != null) {
            this.setUuid(adminSettings.getId().getId());
        }
        this.setCreatedTime(adminSettings.getCreatedTime());
        this.key = adminSettings.getKey();
        this.jsonValue = adminSettings.getJsonValue();
    }

    @Override
    public AdminSettings toData() {
        AdminSettings adminSettings = new AdminSettings(new AdminSettingsId(id));
        adminSettings.setCreatedTime(createdTime);
        adminSettings.setKey(key);
        adminSettings.setJsonValue(jsonValue);
        return adminSettings;
    }

}
