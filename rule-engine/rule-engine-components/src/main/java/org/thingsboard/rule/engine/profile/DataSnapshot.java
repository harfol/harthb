package org.thingsboard.rule.engine.profile;

import lombok.Getter;
import lombok.Setter;
import org.thingsboard.server.common.data.query.EntityKey;
import org.thingsboard.server.common.data.query.EntityKeyType;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

class DataSnapshot {

    private volatile boolean ready;
    @Getter
    @Setter
    private long ts;
    private final Set<EntityKey> keys;
    private final Map<EntityKey, EntityKeyValue> values = new ConcurrentHashMap<>();

    DataSnapshot(Set<EntityKey> entityKeysToFetch) {
        this.keys = entityKeysToFetch;
    }

    void removeValue(EntityKey key) {
        switch (key.getType()) {
            case ATTRIBUTE:
                values.remove(key);
                values.remove(getAttrKey(key, EntityKeyType.CLIENT_ATTRIBUTE));
                values.remove(getAttrKey(key, EntityKeyType.SHARED_ATTRIBUTE));
                values.remove(getAttrKey(key, EntityKeyType.SERVER_ATTRIBUTE));
                break;
            case CLIENT_ATTRIBUTE:
            case SHARED_ATTRIBUTE:
            case SERVER_ATTRIBUTE:
                values.remove(key);
                values.remove(getAttrKey(key, EntityKeyType.ATTRIBUTE));
                break;
            default:
                values.remove(key);
        }
    }

    boolean putValue(EntityKey key, long newTs, EntityKeyValue value) {
        boolean updateOfTs = ts != newTs;
        boolean result = false;
        switch (key.getType()) {
            case ATTRIBUTE:
                result |= putIfKeyExists(key, value, updateOfTs);
                result |= putIfKeyExists(getAttrKey(key, EntityKeyType.CLIENT_ATTRIBUTE), value, updateOfTs);
                result |= putIfKeyExists(getAttrKey(key, EntityKeyType.SHARED_ATTRIBUTE), value, updateOfTs);
                result |= putIfKeyExists(getAttrKey(key, EntityKeyType.SERVER_ATTRIBUTE), value, updateOfTs);
                break;
            case CLIENT_ATTRIBUTE:
            case SHARED_ATTRIBUTE:
            case SERVER_ATTRIBUTE:
                result |= putIfKeyExists(key, value, updateOfTs);
                result |= putIfKeyExists(getAttrKey(key, EntityKeyType.ATTRIBUTE), value, updateOfTs);
                break;
            default:
                result |= putIfKeyExists(key, value, updateOfTs);
        }
        return result;
    }

    private boolean putIfKeyExists(EntityKey key, EntityKeyValue value, boolean updateOfTs) {
        if (keys.contains(key)) {
            EntityKeyValue oldValue = values.put(key, value);
            if (updateOfTs) {
                return true;
            } else {
                return oldValue == null || !oldValue.equals(value);
            }
        } else {
            return false;
        }
    }

    EntityKeyValue getValue(EntityKey key) {
        if (EntityKeyType.ATTRIBUTE.equals(key.getType())) {
            EntityKeyValue value = values.get(key);
            if (value == null) {
                value = values.get(getAttrKey(key, EntityKeyType.CLIENT_ATTRIBUTE));
                if (value == null) {
                    value = values.get(getAttrKey(key, EntityKeyType.SHARED_ATTRIBUTE));
                    if (value == null) {
                        value = values.get(getAttrKey(key, EntityKeyType.SERVER_ATTRIBUTE));
                    }
                }
            }
            return value;
        } else {
            return values.get(key);
        }
    }

    private EntityKey getAttrKey(EntityKey key, EntityKeyType clientAttribute) {
        return new EntityKey(clientAttribute, key.getKey());
    }
}
