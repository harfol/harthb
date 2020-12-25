package org.thingsboard.server.service.security.permission;

import org.thingsboard.server.common.data.EntityType;

import java.util.Optional;

public enum Resource {
    ADMIN_SETTINGS(),
    ALARM(EntityType.ALARM),
    DEVICE(EntityType.DEVICE),
    ASSET(EntityType.ASSET),
    CUSTOMER(EntityType.CUSTOMER),
    DASHBOARD(EntityType.DASHBOARD),
    ENTITY_VIEW(EntityType.ENTITY_VIEW),
    TENANT(EntityType.TENANT),
    RULE_CHAIN(EntityType.RULE_CHAIN),
    USER(EntityType.USER),
    WIDGETS_BUNDLE(EntityType.WIDGETS_BUNDLE),
    WIDGET_TYPE(EntityType.WIDGET_TYPE),
    OAUTH2_CONFIGURATION_INFO(),
    OAUTH2_CONFIGURATION_TEMPLATE(),
    TENANT_PROFILE(EntityType.TENANT_PROFILE),
    DEVICE_PROFILE(EntityType.DEVICE_PROFILE),
    API_USAGE_STATE(EntityType.API_USAGE_STATE);

    private final EntityType entityType;

    Resource() {
        this.entityType = null;
    }

    Resource(EntityType entityType) {
        this.entityType = entityType;
    }

    public Optional<EntityType> getEntityType() {
        return Optional.ofNullable(entityType);
    }

    public static Resource of(EntityType entityType) {
        for (Resource resource : Resource.values()) {
            if (resource.getEntityType().get() == entityType) {
                return resource;
            }
        }
        throw new IllegalArgumentException("Unknown EntityType: " + entityType.name());
    }
}
