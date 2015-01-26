-- User Table
CREATE TABLE user (
    username VARCHAR(20) NOT NULL,
    password VARCHAR(255) NOT NULL,
    salt BINARY(32) NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE card (
    suit VARCHAR(8) NOT NULL,
    rank INTEGER NOT NULL,
    PRIMARY KEY (suit,rank)
);

CREATE TABLE game (
    id BIGINT NOT NULL AUTO_INCREMENT,
    datetime DATE,
    status VARCHAR(10),
    PRIMARY KEY (id)
);

CREATE TABLE playergame (
    id BIGINT NOT NULL AUTO_INCREMENT,
    result VARCHAR(5),
    player_username VARCHAR(20),
    game_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (player_username) REFERENCES user (username),
     FOREIGN KEY (game_id) REFERENCES game (id),
);

CREATE TABLE hand (
    id BIGINT NOT NULL AUTO_INCREMENT,
    handtype VARCHAR(20),
    playerGame_id BIGINT,
    PRIMARY KEY (id),
     FOREIGN KEY (playerGame_id) REFERENCES playergame (id)

);

CREATE TABLE card_hand (
    hand_id BIGINT NOT NULL,
    card_suit VARCHAR(8) NOT NULL,
    card_rank INTEGER NOT NULL,
    PRIMARY KEY (hand_id,card_suit,card_rank),
    FOREIGN KEY (hand_id) REFERENCES hand (id),
    FOREIGN KEY (card_suit, card_rank) REFERENCES card (suit,rank)
);