-- User Table
CREATE TABLE user (
    username VARCHAR(20) NOT NULL,
    password VARCHAR(255) NOT NULL,
    salt BINARY(32) NOT NULL,
    PRIMARY KEY (username)
)