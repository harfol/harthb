package org.thingsboard.server.dao.sql.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.type.PostgresUUIDType;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.thingsboard.server.common.data.EntityType;
import org.thingsboard.server.common.data.id.CustomerId;
import org.thingsboard.server.common.data.id.TenantId;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
public class QuerySecurityContext {

    @Getter
    private final TenantId tenantId;
    @Getter
    private final CustomerId customerId;
    @Getter
    private final EntityType entityType;

}