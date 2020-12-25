ALTER TABLE component_descriptor ADD UNIQUE (clazz);

ALTER TABLE entity_view ALTER COLUMN keys SET DATA TYPE varchar(10000000);
