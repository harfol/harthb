package org.thingsboard.server.dao;

import com.google.common.util.concurrent.ListenableFuture;
import org.thingsboard.server.common.data.id.TenantId;

import java.util.List;
import java.util.UUID;

public interface Dao<T> {

    List<T> find(TenantId tenantId);

    T findById(TenantId tenantId, UUID id);

    ListenableFuture<T> findByIdAsync(TenantId tenantId, UUID id);

    T save(TenantId tenantId, T t);

    boolean removeById(TenantId tenantId, UUID id);

}
