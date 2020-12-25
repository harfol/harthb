package org.thingsboard.rule.engine.api;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class NodeDefinition {

    private String details;
    private String description;
    private boolean inEnabled;
    private boolean outEnabled;
    String[] relationTypes;
    boolean customRelations;
    JsonNode defaultConfiguration;
    String[] uiResources;
    String configDirective;
    String icon;
    String iconUrl;
    String docUrl;

}
