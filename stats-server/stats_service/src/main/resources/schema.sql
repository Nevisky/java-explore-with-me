DROP TABLE IF EXISTS HITS;

CREATE TABLE IF NOT EXISTS HITS
(
    stat_id bigint GENERATED BY DEFAULT AS IDENTITY,
    app varchar NOT NULL,
    ip varchar NOT NULL,
    uri varchar NOT NULL,
    timestamp timestamp WITHOUT TIME ZONE NOT NULL

);