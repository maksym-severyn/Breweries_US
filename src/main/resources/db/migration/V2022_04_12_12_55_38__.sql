CREATE TABLE brewery_entity
(
    b_id          VARCHAR(255) NOT NULL,
    b_address     VARCHAR(255) NULL,
    b_city        VARCHAR(255) NULL,
    b_country     VARCHAR(255) NULL,
    b_keys        VARCHAR(255) NULL,
    b_latitude    DOUBLE       NULL,
    b_longitude   DOUBLE       NULL,
    b_menus       VARCHAR(255) NULL,
    b_name        VARCHAR(255) NULL,
    b_postal_code VARCHAR(255) NULL,
    b_province    VARCHAR(255) NULL,
    b_twitter     VARCHAR(255) NULL,
    b_websites    VARCHAR(255) NULL,
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
    id                BIGINT AUTO_INCREMENT NOT NULL,
    brewery_entity_id VARCHAR(255)          NULL,
    hour              VARCHAR(255)          NULL,
    day               VARCHAR(255)          NULL,
    CONSTRAINT pk_working_day PRIMARY KEY (id)
);

ALTER TABLE working_day
    ADD CONSTRAINT FK_WORKING_DAY_ON_BREWERY_ENTITY FOREIGN KEY (brewery_entity_id) REFERENCES brewery_entity (b_id);

ALTER TABLE brewery_entity_categories
    ADD CONSTRAINT fk_breweryentity_categories_on_brewery_entity FOREIGN KEY (brewery_entity_b_id) REFERENCES brewery_entity (b_id);