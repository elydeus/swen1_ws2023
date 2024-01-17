CREATE DATABASE mctgdb;

\c mctgdb;

CREATE TABLE IF NOT EXISTS users (
    id VARCHAR(255) PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE ,
    password VARCHAR(255) NOT NULL,
    elo INT DEFAULT 100,
    coins INT DEFAULT 20
    );

CREATE TABLE IF NOT EXISTS packages (
    id VARCHAR(255) PRIMARY KEY,
    user_id VARCHAR(255) REFERENCES users(id)
    );

CREATE TABLE IF NOT EXISTS cards (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    damage VARCHAR(255),
    package_id VARCHAR(255) REFERENCES packages(id) ON DELETE SET NULL
    );

CREATE TABLE IF NOT EXISTS stacks (
    card_id VARCHAR(255),
    user_id VARCHAR(255),
    PRIMARY KEY (card_id, user_id),
    FOREIGN KEY (card_id) REFERENCES cards(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS decks (
    deck_id VARCHAR(255) PRIMARY KEY,
    user_id VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id)
    );

CREATE TABLE IF NOT EXISTS deckcards (
    deck_id VARCHAR(255),
    card_id VARCHAR(255),
    PRIMARY KEY (deck_id, card_id),
    FOREIGN KEY (deck_id) REFERENCES decks(deck_id),
    FOREIGN KEY (card_id) REFERENCES cards(id)
    );

ALTER TABLE users ADD COLUMN bio VARCHAR(255) DEFAULT NULL;

ALTER TABLE users ADD COLUMN image VARCHAR(20) DEFAULT NULL;

ALTER TABLE users ADD COLUMN name VARCHAR(20) DEFAULT NULL;


DROP TABLE cards, packages, users, stacks, decks, deckcards