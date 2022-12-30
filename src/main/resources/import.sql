DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (id bigserial, name VARCHAR(255), PRIMARY KEY (id));
INSERT INTO users (name) VALUES ('Bob'), ('Jack'), ('John');

DROP TABLE  IF EXISTS items;
CREATE TABLE IF NOT EXISTS items (id bigserial, title VARCHAR(255), PRIMARY KEY (id));
INSERT INTO items (title) VALUES ('Box');

DROP TABLE if exists products;
CREATE TABLE IF NOT EXISTS products (id bigserial, title VARCHAR(255), price INT, primary key (id));
INSERT INTO products (title, price) VALUES ('Apples', 123), ('Chocolate Bar', 99), ('Milk', 110);


