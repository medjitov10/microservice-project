CREATE TABLE posts (
    id SERIAL,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(50) NOT NULL,
    user_id VARCHAR(100),
    FOREIGN KEY (user_id) REFERENCES users(id)
);