CREATE TABLE users (
    id SERIAL unique,
    email VARCHAR(100) NOT NULL unique,
    password VARCHAR(50) NOT NULL,
    username VARCHAR(50) unique
);