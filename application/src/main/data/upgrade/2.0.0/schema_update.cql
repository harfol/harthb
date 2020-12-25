CREATE TABLE IF NOT EXISTS thingsboard.msg_queue (
    node_id         timeuuid,
    cluster_partition    bigint,
    ts_partition       bigint,
    ts              bigint,
    msg             blob,
	PRIMARY KEY ((node_id, cluster_partition, ts_partition), ts))
WITH CLUSTERING ORDER BY (ts DESC)
AND compaction = {
    'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy',
    'min_threshold': '5',
    'base_time_seconds': '43200',
    'max_window_size_seconds': '43200',
    'tombstone_threshold': '0.9',
    'unchecked_tombstone_compaction': 'true'
};

CREATE TABLE IF NOT EXISTS thingsboard.msg_ack_queue (
    node_id         timeuuid,
    cluster_partition    bigint,
    ts_partition       bigint,
    msg_id              timeuuid,
	PRIMARY KEY ((node_id, cluster_partition, ts_partition), msg_id))
WITH CLUSTERING ORDER BY (msg_id DESC)
AND compaction = {
    'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy',
    'min_threshold': '5',
    'base_time_seconds': '43200',
    'max_window_size_seconds': '43200',
    'tombstone_threshold': '0.9',
    'unchecked_tombstone_compaction': 'true'
};

CREATE TABLE IF NOT EXISTS thingsboard.processed_msg_partitions (
    node_id         timeuuid,
    cluster_partition    bigint,
    ts_partition       bigint,
	PRIMARY KEY ((node_id, cluster_partition), ts_partition))
WITH CLUSTERING ORDER BY (ts_partition DESC)
AND compaction = {
    'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy',
    'min_threshold': '5',
    'base_time_seconds': '43200',
    'max_window_size_seconds': '43200',
    'tombstone_threshold': '0.9',
    'unchecked_tombstone_compaction': 'true'
};

CREATE TABLE IF NOT EXISTS  thingsboard.rule_chain (
    id uuid,
    tenant_id uuid,
    name text,
    search_text text,
    first_rule_node_id uuid,
    root boolean,
    debug_mode boolean,
    configuration text,
    additional_info text,
    PRIMARY KEY (id, tenant_id)
);

CREATE MATERIALIZED VIEW IF NOT EXISTS thingsboard.rule_chain_by_tenant_and_search_text AS
    SELECT *
    from thingsboard.rule_chain
    WHERE tenant_id IS NOT NULL AND search_text IS NOT NULL AND id IS NOT NULL
    PRIMARY KEY ( tenant_id, search_text, id )
    WITH CLUSTERING ORDER BY ( search_text ASC, id DESC );

CREATE TABLE IF NOT EXISTS  thingsboard.rule_node (
    id uuid,
    rule_chain_id uuid,
    type text,
    name text,
    debug_mode boolean,
    search_text text,
    configuration text,
    additional_info text,
    PRIMARY KEY (id)
);

DROP MATERIALIZED VIEW IF EXISTS thingsboard.rule_by_plugin_token;
DROP MATERIALIZED VIEW IF EXISTS thingsboard.rule_by_tenant_and_search_text;
DROP MATERIALIZED VIEW IF EXISTS thingsboard.plugin_by_api_token;
DROP MATERIALIZED VIEW IF EXISTS thingsboard.plugin_by_tenant_and_search_text;

DROP TABLE IF EXISTS thingsboard.rule;
DROP TABLE IF EXISTS thingsboard.plugin;
