DROP TABLE IF EXISTS entity_views;

CREATE TABLE IF NOT EXISTS entity_view (
    id varchar(31) NOT NULL CONSTRAINT entity_view_pkey PRIMARY KEY,
    entity_id varchar(31),
    entity_type varchar(255),
    tenant_id varchar(31),
    customer_id varchar(31),
    type varchar(255),
    name varchar(255),
    keys varchar(255),
    start_ts bigint,
    end_ts bigint,
    search_text varchar(255),
    additional_info varchar
);
