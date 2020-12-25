package org.thingsboard.server.dao.sql.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.thingsboard.server.dao.model.sql.EventEntity;
import org.thingsboard.server.dao.util.PsqlDao;

@Slf4j
@PsqlDao
@Repository
public class PsqlEventInsertRepository extends AbstractEventInsertRepository {

    private static final String P_KEY_CONFLICT_STATEMENT = "(id)";
    private static final String UNQ_KEY_CONFLICT_STATEMENT = "(tenant_id, created_time, entity_type, entity_id, event_type, event_uid)";

    private static final String UPDATE_P_KEY_STATEMENT = "id = :id";
    private static final String UPDATE_UNQ_KEY_STATEMENT = "created_time = :created_time, tenant_id = :tenant_id, entity_type = :entity_type, entity_id = :entity_id, event_type = :event_type, event_uid = :event_uid";

    private static final String INSERT_OR_UPDATE_ON_P_KEY_CONFLICT = getInsertOrUpdateString(P_KEY_CONFLICT_STATEMENT, UPDATE_UNQ_KEY_STATEMENT);
    private static final String INSERT_OR_UPDATE_ON_UNQ_KEY_CONFLICT = getInsertOrUpdateString(UNQ_KEY_CONFLICT_STATEMENT, UPDATE_P_KEY_STATEMENT);

    @Override
    public EventEntity saveOrUpdate(EventEntity entity) {
        return saveAndGet(entity, INSERT_OR_UPDATE_ON_P_KEY_CONFLICT, INSERT_OR_UPDATE_ON_UNQ_KEY_CONFLICT);
    }

    @Override
    protected EventEntity doProcessSaveOrUpdate(EventEntity entity, String query) {
        return (EventEntity) getQuery(entity, query).getSingleResult();

    }

    private static String getInsertOrUpdateString(String eventKeyStatement, String updateKeyStatement) {
        return "INSERT INTO event (id, created_time, body, entity_id, entity_type, event_type, event_uid, tenant_id, ts) " +
                "VALUES (:id, :created_time, :body, :entity_id, :entity_type, :event_type, :event_uid, :tenant_id, :ts) " +
                "ON CONFLICT " + eventKeyStatement + " DO UPDATE SET body = :body, ts = :ts," + updateKeyStatement + " returning *";
    }
}