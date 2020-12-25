CREATE INDEX IF NOT EXISTS idx_alarm_originator_alarm_type ON alarm(originator_id, type, start_ts DESC);

CREATE INDEX IF NOT EXISTS idx_alarm_originator_created_time ON alarm(originator_id, created_time DESC);

CREATE INDEX IF NOT EXISTS idx_alarm_tenant_created_time ON alarm(tenant_id, created_time DESC);

CREATE INDEX IF NOT EXISTS idx_alarm_tenant_alarm_type_created_time ON alarm(tenant_id, type, created_time DESC);

CREATE INDEX IF NOT EXISTS idx_event_type_entity_id ON event(tenant_id, event_type, entity_type, entity_id);

CREATE INDEX IF NOT EXISTS idx_relation_to_id ON relation(relation_type_group, to_type, to_id);

CREATE INDEX IF NOT EXISTS idx_relation_from_id ON relation(relation_type_group, from_type, from_id);

CREATE INDEX IF NOT EXISTS idx_device_customer_id ON device(tenant_id, customer_id);

CREATE INDEX IF NOT EXISTS idx_device_customer_id_and_type ON device(tenant_id, customer_id, type);

CREATE INDEX IF NOT EXISTS idx_device_type ON device(tenant_id, type);

CREATE INDEX IF NOT EXISTS idx_device_device_profile_id ON device(tenant_id, device_profile_id);

CREATE INDEX IF NOT EXISTS idx_asset_customer_id ON asset(tenant_id, customer_id);

CREATE INDEX IF NOT EXISTS idx_asset_customer_id_and_type ON asset(tenant_id, customer_id, type);

CREATE INDEX IF NOT EXISTS idx_asset_type ON asset(tenant_id, type);

CREATE INDEX IF NOT EXISTS idx_attribute_kv_by_key_and_last_update_ts ON attribute_kv(entity_id, attribute_key, last_update_ts desc);
