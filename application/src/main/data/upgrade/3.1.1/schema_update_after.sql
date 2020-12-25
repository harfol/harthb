DROP PROCEDURE IF EXISTS update_tenant_profiles;
DROP PROCEDURE IF EXISTS update_device_profiles;

ALTER TABLE tenant ALTER COLUMN tenant_profile_id SET NOT NULL;
ALTER TABLE tenant DROP CONSTRAINT IF EXISTS fk_tenant_profile;
ALTER TABLE tenant ADD CONSTRAINT fk_tenant_profile FOREIGN KEY (tenant_profile_id) REFERENCES tenant_profile(id);
ALTER TABLE tenant DROP COLUMN IF EXISTS isolated_tb_core;
ALTER TABLE tenant DROP COLUMN IF EXISTS isolated_tb_rule_engine;

ALTER TABLE device ALTER COLUMN device_profile_id SET NOT NULL;
ALTER TABLE device DROP CONSTRAINT IF EXISTS fk_device_profile;
ALTER TABLE device ADD CONSTRAINT fk_device_profile FOREIGN KEY (device_profile_id) REFERENCES device_profile(id);
