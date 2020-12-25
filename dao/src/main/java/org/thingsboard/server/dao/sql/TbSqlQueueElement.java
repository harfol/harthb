package org.thingsboard.server.dao.sql;

import com.google.common.util.concurrent.SettableFuture;
import lombok.Getter;

public final class TbSqlQueueElement<E> {
    @Getter
    private final SettableFuture<Void> future;
    @Getter
    private final E entity;

    public TbSqlQueueElement(SettableFuture<Void> future, E entity) {
        this.future = future;
        this.entity = entity;
    }
}


