CREATE INDEX IF NOT EXISTS idx_alarm_tenant_alarm_type_created_time ON alarm(tenant_id, type, created_time DESC);
