DROP INDEX IF EXISTS idx_alarm_originator_alarm_type;

CREATE INDEX IF NOT EXISTS idx_alarm_originator_alarm_type ON alarm(originator_id, type, start_ts DESC);

CREATE INDEX IF NOT EXISTS idx_device_customer_id ON device(tenant_id, customer_id);

CREATE INDEX IF NOT EXISTS idx_device_customer_id_and_type ON device(tenant_id, customer_id, type);

CREATE INDEX IF NOT EXISTS idx_device_type ON device(tenant_id, type);

CREATE INDEX IF NOT EXISTS idx_asset_customer_id ON asset(tenant_id, customer_id);

CREATE INDEX IF NOT EXISTS idx_asset_customer_id_and_type ON asset(tenant_id, customer_id, type);

CREATE INDEX IF NOT EXISTS idx_asset_type ON asset(tenant_id, type);