--liquibase formatted sql

--changeset artem:1
CREATE TABLE IF NOT EXISTS users
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(64) NOT NULL,
    email VARCHAR(64) NOT NULL,
    phone VARCHAR(64) NOT NULL
);

--changeset artem:2
CREATE TABLE IF NOT EXISTS item
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL UNIQUE
);

--changeset artem:3
CREATE TABLE IF NOT EXISTS duration
(
    id           SERIAL PRIMARY KEY,
    months_count INT
);

--changeset artem:4
CREATE TABLE IF NOT EXISTS price
(
    id          SERIAL PRIMARY KEY,
    item_id     INT REFERENCES item,
    amount      INT,
    duration_id INT REFERENCES duration
);

--changeset artem:5
CREATE TABLE IF NOT EXISTS subscription
(
    id         SERIAL PRIMARY KEY,
    user_id    INT REFERENCES users ON DELETE CASCADE,
    item_id    INT REFERENCES item,
    status     VARCHAR(32),
    start_time DATE,
    end_time   DATE
);

--changeset artem:6
CREATE TABLE IF NOT EXISTS orders
(
    id              SERIAL PRIMARY KEY,
    subscription_id INT REFERENCES subscription ON DELETE CASCADE,
    price_id        INT REFERENCES price
);
