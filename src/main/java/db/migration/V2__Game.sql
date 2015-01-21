CREATE TABLE card (
    suit VARCHAR(5) NOT NULL,
    rank INTEGER NOT NULL,
    PRIMARY KEY (suit,rank)
);

CREATE TABLE hand (
    id BIGINT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
);

CREATE TABLE game (
    id BIGINT NOT NULL AUTO_INCREMENT,
    datetime DATE,
    PRIMARY KEY (id)
);

CREATE TABLE playergame (
    id BIGINT NOT NULL AUTO_INCREMENT,
    player_username VARCHAR(20),
    game_id BIGINT,
    hand_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (player_username) REFERENCES user (username),
     FOREIGN KEY (game_id) REFERENCES game (id),
      FOREIGN KEY (hand_id) REFERENCES hand (id)
)