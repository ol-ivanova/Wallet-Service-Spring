--liquibase formatted sql

--changeset oivanova:1
CREATE TABLE player
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(255)        NOT NULL,
    password VARCHAR(255)        NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL
);

--changeset oivanova:2
CREATE TABLE player_account
(
    account_number UUID PRIMARY KEY,
    balance        NUMERIC(38, 2),
    player_id      INT REFERENCES player (id)
);

--changeset oivanova:3
CREATE TABLE player_audit
(
    id        SERIAL PRIMARY KEY,
    action    VARCHAR(255) NOT NULL,
    date_time TIMESTAMP,
    player_id INT REFERENCES player (id)
);

--changeset oivanova:4
CREATE TABLE transaction
(
    id                  SERIAL PRIMARY KEY,
    created_date        TIMESTAMP,
    player_account_from UUID REFERENCES player_account (account_number),
    player_account_to   UUID REFERENCES player_account (account_number),
    sum                 NUMERIC(38, 2),
    type                VARCHAR(20)
);