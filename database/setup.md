CREATE TABLE heat (
  "id" integer not null,
  "device_id" integer not null,
  "heat_val" float not null,
  "time" timestamp not null
)
WITH (
  timescaledb.hypertable,
  timescaledb.partition_column='time'
);

CREATE TABLE co2 (
  "id" integer not null,
  "device_id" integer not null,
  "co2_val" float not null,
  "time" timestamp not null
)
WITH (
  timescaledb.hypertable,
  timescaledb.partition_column='time'
);

CREATE TABLE humidity (
  "id" integer not null,
  "device_id" integer not null,
  "moisture_val" float not null,
  "time" timestamp not null
)
WITH (
  timescaledb.hypertable,
  timescaledb.partition_column='time'
);

CREATE TABLE moisture (
  "id" integer not null,
  "device_id" integer not null,
  "moisture_val" float not null,
  "time" timestamp not null
)
WITH (
  timescaledb.hypertable,
  timescaledb.partition_column='time'
);