package org.thingsboard.server.common.data.query;

public enum EntityFilterType {
    SINGLE_ENTITY("singleEntity"),
    ENTITY_LIST("entityList"),
    ENTITY_NAME("entityName"),
    ASSET_TYPE("assetType"),
    DEVICE_TYPE("deviceType"),
    ENTITY_VIEW_TYPE("entityViewType"),
    RELATIONS_QUERY("relationsQuery"),
    ASSET_SEARCH_QUERY("assetSearchQuery"),
    DEVICE_SEARCH_QUERY("deviceSearchQuery"),
    ENTITY_VIEW_SEARCH_QUERY("entityViewSearchQuery"),
    API_USAGE_STATE("apiUsageState");

    private final String label;

    EntityFilterType(String label) {
        this.label = label;
    }
}
