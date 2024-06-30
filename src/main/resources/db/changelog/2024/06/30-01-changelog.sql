-- liquibase formatted sql

-- changeset ainu:1719770444130-1
CREATE TABLE encounter
(
    id                  UUID         NOT NULL,
    version             BIGINT,
    created_date        BYTEA,
    last_modified_date  BYTEA,
    creator_snowflake   BIGINT       NOT NULL,
    editor_snowflake    BIGINT,
    encounter_name      VARCHAR(255) NOT NULL,
    scheduled_encounter BOOLEAN,
    prime_encounter     BOOLEAN,
    encounter_weight    UUID         NOT NULL,
    guild_id            UUID         NOT NULL,
    CONSTRAINT pk_encounter PRIMARY KEY (id)
);

-- changeset ainu:1719770444130-2
CREATE TABLE encounter_spawn
(
    id                 UUID   NOT NULL,
    version            BIGINT,
    created_date       BYTEA,
    last_modified_date BYTEA,
    creator_snowflake  BIGINT NOT NULL,
    editor_snowflake   BIGINT,
    encounter_id       UUID,
    day_of_week        VARCHAR(255),
    CONSTRAINT pk_encounter_spawn PRIMARY KEY (id)
);

-- changeset ainu:1719770444130-3
CREATE TABLE encounter_spawn_time
(
    id                 UUID   NOT NULL,
    version            BIGINT,
    created_date       BYTEA,
    last_modified_date BYTEA,
    creator_snowflake  BIGINT NOT NULL,
    editor_snowflake   BIGINT,
    offset_time        time WITHOUT TIME ZONE NOT NULL,
    encounter_spawn_id UUID   NOT NULL,
    CONSTRAINT pk_encounter_spawn_time PRIMARY KEY (id)
);

-- changeset ainu:1719770444130-4
CREATE TABLE encounter_weight
(
    id                 UUID    NOT NULL,
    version            BIGINT,
    created_date       BYTEA,
    last_modified_date BYTEA,
    creator_snowflake  BIGINT  NOT NULL,
    editor_snowflake   BIGINT,
    encounter_weight   INTEGER NOT NULL,
    CONSTRAINT pk_encounter_weight PRIMARY KEY (id)
);

-- changeset ainu:1719770444130-5
CREATE TABLE event_attendance
(
    id                 UUID   NOT NULL,
    version            BIGINT,
    created_date       BYTEA,
    last_modified_date BYTEA,
    creator_snowflake  BIGINT NOT NULL,
    editor_snowflake   BIGINT,
    event_id           UUID   NOT NULL,
    guild_member_id    UUID   NOT NULL,
    CONSTRAINT pk_event_attendance PRIMARY KEY (id)
);

-- changeset ainu:1719770444130-6
CREATE TABLE guild
(
    id                     UUID   NOT NULL,
    version                BIGINT,
    created_date           BYTEA,
    last_modified_date     BYTEA,
    creator_snowflake      BIGINT NOT NULL,
    editor_snowflake       BIGINT,
    guild_snowflake        BIGINT NOT NULL,
    guild_configuration_id UUID,
    CONSTRAINT pk_guild PRIMARY KEY (id)
);

-- changeset ainu:1719770444130-7
CREATE TABLE guild_administrator_role
(
    id                     UUID   NOT NULL,
    version                BIGINT,
    created_date           BYTEA,
    last_modified_date     BYTEA,
    creator_snowflake      BIGINT NOT NULL,
    editor_snowflake       BIGINT,
    guild_configuration_id UUID   NOT NULL,
    role_snowflake         BIGINT NOT NULL,
    CONSTRAINT pk_guild_administrator_role PRIMARY KEY (id)
);

-- changeset ainu:1719770444130-8
CREATE TABLE guild_configuration
(
    id                         UUID   NOT NULL,
    version                    BIGINT,
    created_date               BYTEA,
    last_modified_date         BYTEA,
    creator_snowflake          BIGINT NOT NULL,
    editor_snowflake           BIGINT,
    guild_id                   UUID   NOT NULL,
    stash_enabled              BOOLEAN,
    attendance_enabled         BOOLEAN,
    loot_pretending_days_delay INTEGER,
    CONSTRAINT pk_guild_configuration PRIMARY KEY (id)
);

-- changeset ainu:1719770444130-9
CREATE TABLE guild_event
(
    id                    UUID   NOT NULL,
    version               BIGINT,
    created_date          BYTEA,
    last_modified_date    BYTEA,
    creator_snowflake     BIGINT NOT NULL,
    editor_snowflake      BIGINT,
    guild_event_encounter UUID   NOT NULL,
    event_owner_guild     UUID   NOT NULL,
    CONSTRAINT pk_guild_event PRIMARY KEY (id)
);

-- changeset ainu:1719770444130-10
CREATE TABLE guild_member
(
    id                 UUID   NOT NULL,
    version            BIGINT,
    created_date       BYTEA,
    last_modified_date BYTEA,
    creator_snowflake  BIGINT NOT NULL,
    editor_snowflake   BIGINT,
    member_guild       UUID   NOT NULL,
    member             UUID   NOT NULL,
    member_join_date   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    member_suspended   BOOLEAN,
    CONSTRAINT pk_guild_member PRIMARY KEY (id)
);

-- changeset ainu:1719770444130-11
CREATE TABLE guild_member_note
(
    id                 UUID   NOT NULL,
    version            BIGINT,
    created_date       BYTEA,
    last_modified_date BYTEA,
    creator_snowflake  BIGINT NOT NULL,
    editor_snowflake   BIGINT,
    note_text          VARCHAR(255),
    visible_to_member  BOOLEAN,
    member_id          UUID,
    CONSTRAINT pk_guild_member_note PRIMARY KEY (id)
);

-- changeset ainu:1719770444130-12
CREATE TABLE guild_member_role
(
    id                     UUID   NOT NULL,
    version                BIGINT,
    created_date           BYTEA,
    last_modified_date     BYTEA,
    creator_snowflake      BIGINT NOT NULL,
    editor_snowflake       BIGINT,
    guild_configuration_id UUID   NOT NULL,
    role_snowflake         BIGINT NOT NULL,
    CONSTRAINT pk_guild_member_role PRIMARY KEY (id)
);

-- changeset ainu:1719770444130-13
CREATE TABLE guild_moderator_role
(
    id                     UUID   NOT NULL,
    version                BIGINT,
    created_date           BYTEA,
    last_modified_date     BYTEA,
    creator_snowflake      BIGINT NOT NULL,
    editor_snowflake       BIGINT,
    guild_configuration_id UUID   NOT NULL,
    role_snowflake         BIGINT NOT NULL,
    CONSTRAINT pk_guild_moderator_role PRIMARY KEY (id)
);

-- changeset ainu:1719770444130-14
CREATE TABLE item_model
(
    id                 UUID         NOT NULL,
    version            BIGINT,
    created_date       BYTEA,
    last_modified_date BYTEA,
    creator_snowflake  BIGINT       NOT NULL,
    editor_snowflake   BIGINT,
    item_name          VARCHAR(255) NOT NULL,
    item_price         INTEGER,
    synthesized_item   BOOLEAN,
    item_exp_needed    INTEGER,
    countable_item     BOOLEAN,
    item_quantity      INTEGER,
    guild_id           UUID,
    CONSTRAINT pk_item_model PRIMARY KEY (id)
);

-- changeset ainu:1719770444130-15
CREATE TABLE member
(
    id                   UUID   NOT NULL,
    version              BIGINT,
    created_date         BYTEA,
    last_modified_date   BYTEA,
    creator_snowflake    BIGINT NOT NULL,
    editor_snowflake     BIGINT,
    member_snowflake     BIGINT NOT NULL,
    member_configuration UUID,
    CONSTRAINT pk_member PRIMARY KEY (id)
);

-- changeset ainu:1719770444130-16
CREATE TABLE member_configuration
(
    id                   UUID   NOT NULL,
    version              BIGINT,
    created_date         BYTEA,
    last_modified_date   BYTEA,
    creator_snowflake    BIGINT NOT NULL,
    editor_snowflake     BIGINT,
    member_default_guild UUID,
    CONSTRAINT pk_member_configuration PRIMARY KEY (id)
);

-- changeset ainu:1719770444130-17
CREATE TABLE personalized_item
(
    id                 UUID         NOT NULL,
    version            BIGINT,
    created_date       BYTEA,
    last_modified_date BYTEA,
    creator_snowflake  BIGINT       NOT NULL,
    editor_snowflake   BIGINT,
    item_name          VARCHAR(255) NOT NULL,
    item_price         INTEGER,
    synthesized_item   BOOLEAN,
    item_exp_needed    INTEGER,
    countable_item     BOOLEAN,
    item_quantity      INTEGER,
    guild_id           UUID,
    item_available     BOOLEAN,
    item_owner         UUID,
    item_exp_inserted  INTEGER      NOT NULL,
    CONSTRAINT pk_personalized_item PRIMARY KEY (id)
);

-- changeset ainu:1719770444130-18
ALTER TABLE encounter
    ADD CONSTRAINT uc_encounter_encounter_weight UNIQUE (encounter_weight);

-- changeset ainu:1719770444130-19
ALTER TABLE encounter_weight
    ADD CONSTRAINT uc_encounter_weight_encounter_weight UNIQUE (encounter_weight);

-- changeset ainu:1719770444130-20
ALTER TABLE guild_configuration
    ADD CONSTRAINT uc_guild_configuration_guild UNIQUE (guild_id);

-- changeset ainu:1719770444130-21
ALTER TABLE guild
    ADD CONSTRAINT uc_guild_guild_configuration UNIQUE (guild_configuration_id);

-- changeset ainu:1719770444130-22
ALTER TABLE guild_member
    ADD CONSTRAINT uc_guild_member_member UNIQUE (member);

-- changeset ainu:1719770444130-23
ALTER TABLE member_configuration
    ADD CONSTRAINT uc_member_configuration_member_default_guild UNIQUE (member_default_guild);

-- changeset ainu:1719770444130-24
ALTER TABLE member
    ADD CONSTRAINT uc_member_member_configuration UNIQUE (member_configuration);

-- changeset ainu:1719770444130-25
ALTER TABLE encounter
    ADD CONSTRAINT FK_ENCOUNTER_ON_ENCOUNTER_WEIGHT FOREIGN KEY (encounter_weight) REFERENCES encounter_weight (id);

-- changeset ainu:1719770444130-26
ALTER TABLE encounter
    ADD CONSTRAINT FK_ENCOUNTER_ON_GUILD FOREIGN KEY (guild_id) REFERENCES guild (id);

-- changeset ainu:1719770444130-27
ALTER TABLE encounter_spawn
    ADD CONSTRAINT FK_ENCOUNTER_SPAWN_ON_ENCOUNTER FOREIGN KEY (encounter_id) REFERENCES encounter (id);

-- changeset ainu:1719770444130-28
ALTER TABLE encounter_spawn_time
    ADD CONSTRAINT FK_ENCOUNTER_SPAWN_TIME_ON_ENCOUNTER_SPAWN FOREIGN KEY (encounter_spawn_id) REFERENCES encounter_spawn (id);

-- changeset ainu:1719770444130-29
ALTER TABLE event_attendance
    ADD CONSTRAINT FK_EVENT_ATTENDANCE_ON_EVENT FOREIGN KEY (event_id) REFERENCES guild_event (id);

-- changeset ainu:1719770444130-30
ALTER TABLE event_attendance
    ADD CONSTRAINT FK_EVENT_ATTENDANCE_ON_GUILD_MEMBER FOREIGN KEY (guild_member_id) REFERENCES guild_member (id);

-- changeset ainu:1719770444130-31
ALTER TABLE guild_administrator_role
    ADD CONSTRAINT FK_GUILD_ADMINISTRATOR_ROLE_ON_GUILD_CONFIGURATION FOREIGN KEY (guild_configuration_id) REFERENCES guild_configuration (id);

-- changeset ainu:1719770444130-32
ALTER TABLE guild_configuration
    ADD CONSTRAINT FK_GUILD_CONFIGURATION_ON_GUILD FOREIGN KEY (guild_id) REFERENCES guild (id);

-- changeset ainu:1719770444130-33
ALTER TABLE guild_event
    ADD CONSTRAINT FK_GUILD_EVENT_ON_EVENT_OWNER_GUILD FOREIGN KEY (event_owner_guild) REFERENCES guild (id);

-- changeset ainu:1719770444130-34
ALTER TABLE guild_event
    ADD CONSTRAINT FK_GUILD_EVENT_ON_GUILD_EVENT_ENCOUNTER FOREIGN KEY (guild_event_encounter) REFERENCES encounter (id);

-- changeset ainu:1719770444130-35
ALTER TABLE guild_member_note
    ADD CONSTRAINT FK_GUILD_MEMBER_NOTE_ON_MEMBER FOREIGN KEY (member_id) REFERENCES guild_member (id);

-- changeset ainu:1719770444130-36
ALTER TABLE guild_member
    ADD CONSTRAINT FK_GUILD_MEMBER_ON_MEMBER FOREIGN KEY (member) REFERENCES member (id);

-- changeset ainu:1719770444130-37
ALTER TABLE guild_member
    ADD CONSTRAINT FK_GUILD_MEMBER_ON_MEMBER_GUILD FOREIGN KEY (member_guild) REFERENCES guild (id);

-- changeset ainu:1719770444130-38
ALTER TABLE guild_member_role
    ADD CONSTRAINT FK_GUILD_MEMBER_ROLE_ON_GUILD_CONFIGURATION FOREIGN KEY (guild_configuration_id) REFERENCES guild_configuration (id);

-- changeset ainu:1719770444130-39
ALTER TABLE guild_moderator_role
    ADD CONSTRAINT FK_GUILD_MODERATOR_ROLE_ON_GUILD_CONFIGURATION FOREIGN KEY (guild_configuration_id) REFERENCES guild_configuration (id);

-- changeset ainu:1719770444130-40
ALTER TABLE guild
    ADD CONSTRAINT FK_GUILD_ON_GUILD_CONFIGURATION FOREIGN KEY (guild_configuration_id) REFERENCES guild_configuration (id);

-- changeset ainu:1719770444130-41
ALTER TABLE item_model
    ADD CONSTRAINT FK_ITEM_MODEL_ON_GUILD FOREIGN KEY (guild_id) REFERENCES guild (id);

-- changeset ainu:1719770444130-42
ALTER TABLE member_configuration
    ADD CONSTRAINT FK_MEMBER_CONFIGURATION_ON_MEMBER_DEFAULT_GUILD FOREIGN KEY (member_default_guild) REFERENCES guild (id);

-- changeset ainu:1719770444130-43
ALTER TABLE member
    ADD CONSTRAINT FK_MEMBER_ON_MEMBER_CONFIGURATION FOREIGN KEY (member_configuration) REFERENCES member_configuration (id);

-- changeset ainu:1719770444130-44
ALTER TABLE personalized_item
    ADD CONSTRAINT FK_PERSONALIZED_ITEM_ON_GUILD FOREIGN KEY (guild_id) REFERENCES guild (id);

-- changeset ainu:1719770444130-45
ALTER TABLE personalized_item
    ADD CONSTRAINT FK_PERSONALIZED_ITEM_ON_ITEM_OWNER FOREIGN KEY (item_owner) REFERENCES guild_member (id);

