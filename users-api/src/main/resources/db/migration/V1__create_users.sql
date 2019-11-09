CREATE TABLE users (
    id SERIAL,
    email VARCHAR(100) NOT NULL unique,
    password VARCHAR(50) NOT NULL,
    username VARCHAR(50) unique
);