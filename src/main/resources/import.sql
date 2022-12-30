DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (id bigserial, name VARCHAR(255), PRIMARY KEY (id));
INSERT INTO users (name) VALUES ('Bob'), ('Jack'), ('John');

DROP TABLE  IF EXISTS items;
CREATE TABLE IF NOT EXISTS items (id bigserial, title VARCHAR(255), PRIMARY KEY (id));
INSERT INTO items (title) VALUES ('Box');


