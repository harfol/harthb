package org.thingsboard.server.service.component;

import org.thingsboard.server.common.data.plugin.ComponentDescriptor;
import org.thingsboard.server.common.data.plugin.ComponentType;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Andrew Shvayka
 */
public interface ComponentDiscoveryService {

    void discoverComponents();

    List<ComponentDescriptor> getComponents(ComponentType type);

    List<ComponentDescriptor> getComponents(Set<ComponentType> types);

    Optional<ComponentDescriptor> getComponent(String clazz);

}
