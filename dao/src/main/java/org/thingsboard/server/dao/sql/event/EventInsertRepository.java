package org.thingsboard.server.dao.sql.event;

import org.thingsboard.server.dao.model.sql.EventEntity;

public interface EventInsertRepository {

    EventEntity saveOrUpdate(EventEntity entity);

}
