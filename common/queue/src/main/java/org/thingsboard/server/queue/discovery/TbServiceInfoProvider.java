package org.thingsboard.server.queue.discovery;

import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.msg.queue.ServiceType;
import org.thingsboard.server.gen.transport.TransportProtos.ServiceInfo;

import java.util.Optional;

public interface TbServiceInfoProvider {

    String getServiceId();

    ServiceInfo getServiceInfo();

    boolean isService(ServiceType serviceType);

    Optional<TenantId> getIsolatedTenant();

}
