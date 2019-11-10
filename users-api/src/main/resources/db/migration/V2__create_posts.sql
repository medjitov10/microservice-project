CREATE TABLE posts (
    id SERIAL unique,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(50) NOT NULL,
    user_id int,
    FOREIGN KEY (user_id) REFERENCES users(id)
);