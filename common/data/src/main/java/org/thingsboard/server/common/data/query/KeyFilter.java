package org.thingsboard.server.common.data.query;

import lombok.Data;

@Data
public class KeyFilter {

    private EntityKey key;
    private EntityKeyValueType valueType;
    private KeyFilterPredicate predicate;

}
