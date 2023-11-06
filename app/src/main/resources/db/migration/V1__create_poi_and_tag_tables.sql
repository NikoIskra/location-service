CREATE SEQUENCE IF NOT EXISTS poi_id_seq
    INCREMENT 1
    START 1000
    MINVALUE 1
    CACHE 1;

    CREATE SEQUENCE IF NOT EXISTS tag_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    CACHE 1;

    CREATE TABLE IF NOT EXISTS poi
(
    id bigint NOT NULL DEFAULT nextval('poi_id_seq'),
    external_id character varying(36) NOT NULL,
    name character varying(64) NOT NULL,
    type character varying(32) NOT NULL,
    description character varying(512) ,
    latitude real NOT NULL,
    longitude real NOT NULL,
    location geography(point) NOT NULL,
    status character varying(8)  NOT NULL,
    created_at timestamp without time zone DEFAULT now(),
    updated_at timestamp without time zone,
    CONSTRAINT poi_pkey PRIMARY KEY (id),
    CONSTRAINT external_id_name UNIQUE (external_id, name)
);

CREATE TABLE IF NOT EXISTS tag
(
    id bigint NOT NULL DEFAULT nextval('tag_id_seq'),
    poi_id bigint NOT NULL,
    name character varying(32) NOT NULL,
    CONSTRAINT tag_pkey PRIMARY KEY (id),
    CONSTRAINT poi_id_name UNIQUE (poi_id, name),
    CONSTRAINT poi_id_fk FOREIGN KEY (poi_id)
        REFERENCES poi (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);