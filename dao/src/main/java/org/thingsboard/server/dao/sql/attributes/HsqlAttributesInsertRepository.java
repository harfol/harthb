package org.thingsboard.server.dao.sql.attributes;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.thingsboard.server.dao.model.sql.AttributeKvEntity;
import org.thingsboard.server.dao.util.HsqlDao;

import java.sql.Types;
import java.util.List;

@HsqlDao
@Repository
@Transactional
public class HsqlAttributesInsertRepository extends AttributeKvInsertRepository {

    private static final String INSERT_OR_UPDATE =
            "MERGE INTO attribute_kv USING(VALUES ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                    "A (entity_type, entity_id, attribute_type, attribute_key, str_v, long_v, dbl_v, bool_v, json_v, last_update_ts) " +
                    "ON (attribute_kv.entity_type=A.entity_type " +
                    "AND attribute_kv.entity_id=A.entity_id " +
                    "AND attribute_kv.attribute_type=A.attribute_type " +
                    "AND attribute_kv.attribute_key=A.attribute_key) " +
                    "WHEN MATCHED THEN UPDATE SET attribute_kv.str_v = A.str_v, attribute_kv.long_v = A.long_v, attribute_kv.dbl_v = A.dbl_v, attribute_kv.bool_v = A.bool_v, attribute_kv.json_v = A.json_v, attribute_kv.last_update_ts = A.last_update_ts " +
                    "WHEN NOT MATCHED THEN INSERT (entity_type, entity_id, attribute_type, attribute_key, str_v, long_v, dbl_v, bool_v, json_v, last_update_ts) " +
                    "VALUES (A.entity_type, A.entity_id, A.attribute_type, A.attribute_key, A.str_v, A.long_v, A.dbl_v, A.bool_v, A.json_v, A.last_update_ts)";

    @Override
    protected void saveOrUpdate(List<AttributeKvEntity> entities) {
        entities.forEach(entity -> {
            jdbcTemplate.update(INSERT_OR_UPDATE, ps -> {
                ps.setString(1, entity.getId().getEntityType().name());
                ps.setObject(2, entity.getId().getEntityId());
                ps.setString(3, entity.getId().getAttributeType());
                ps.setString(4, entity.getId().getAttributeKey());
                ps.setString(5, entity.getStrValue());

                if (entity.getLongValue() != null) {
                    ps.setLong(6, entity.getLongValue());
                } else {
                    ps.setNull(6, Types.BIGINT);
                }

                if (entity.getDoubleValue() != null) {
                    ps.setDouble(7, entity.getDoubleValue());
                } else {
                    ps.setNull(7, Types.DOUBLE);
                }

                if (entity.getBooleanValue() != null) {
                    ps.setBoolean(8, entity.getBooleanValue());
                } else {
                    ps.setNull(8, Types.BOOLEAN);
                }

                ps.setString(9, entity.getJsonValue());

                ps.setLong(10, entity.getLastUpdateTs());
            });
        });
    }
}