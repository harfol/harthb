CREATE INDEX IF NOT EXISTS idx_alarm_originator_alarm_type ON alarm(tenant_id, type, originator_type, originator_id);

CREATE INDEX IF NOT EXISTS idx_event_type_entity_id ON event(tenant_id, event_type, entity_type, entity_id);

CREATE INDEX IF NOT EXISTS idx_relation_to_id ON relation(relation_type_group, to_type, to_id);

CREATE INDEX IF NOT EXISTS idx_relation_from_id ON relation(relation_type_group, from_type, from_id);
