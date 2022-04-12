CREATE TABLE brewery_entity
(
    b_id          VARCHAR(255) NOT NULL,
    b_address     VARCHAR(255) NULL,
    b_city        VARCHAR(255) NULL,
    b_country     VARCHAR(255) NULL,
    b_keys        VARCHAR(255) NULL,
    b_latitude    DOUBLE       NULL,
    b_longitude   DOUBLE       NULL,
    b_menus       TEXT         NULL,
    b_name        VARCHAR(255) NULL,
    b_postal_code VARCHAR(255) NULL,
    b_province    VARCHAR(255) NULL,
    b_twitter     VARCHAR(255) NULL,
    b_websites    TEXT         NULL,
    b_state       VARCHAR(255) NULL,
    CONSTRAINT pk_brewery_entity PRIMARY KEY (b_id)
);

CREATE TABLE brewery_entity_categories
(
    brewery_entity_b_id VARCHAR(255) NOT NULL,
    b_categories        VARCHAR(255) NULL
);

CREATE TABLE working_day
(
    wd_id                   BIGINT       NOT NULL,
    owned_brewery_entity_id VARCHAR(255) NULL,
    wd_hour                 VARCHAR(255) NULL,
    wd_day                  VARCHAR(255) NULL,
    CONSTRAINT pk_working_day PRIMARY KEY (wd_id)
);

ALTER TABLE working_day
    ADD CONSTRAINT FK_WORKING_DAY_ON_OWNED_BREWERY_ENTITY FOREIGN KEY (owned_brewery_entity_id) REFERENCES brewery_entity (b_id);

ALTER TABLE brewery_entity_categories
    ADD CONSTRAINT fk_brewery_entity_categories_on_brewery_entity FOREIGN KEY (brewery_entity_b_id) REFERENCES brewery_entity (b_id);