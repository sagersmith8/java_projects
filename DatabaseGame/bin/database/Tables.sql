My database is used for a game. It contains fields users and stores there information
when they log in. When they log in the game will be able to start right from where they
left off.


CREATE TABLE GAME_PEICE(game_peice_id NUMBER NOT NULL, image BLOB NOT NULL, level_at NUMBER NOT NULL,
price NUMBER NOT NULL, health NUMBER NOT NULL, attack NUMBER NOT NULL, upgrade_price NUMBER NOT NULL, 
name VARCHAR2(45) NOT NULL, CONSTRAINT pk_game_id PRIMARY KEY(game_peice_id), 
CONSTRAINT fk_game_piece_id FOREIGN KEY(game_peice_id) REFERENCES GAME_PEICE(game_piece_id));

DESC GAME_PEICE;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------

 GAME_PEICE_ID                                      NUMBER
 IMAGE                                              BLOB
 LEVEL_AT                                           NUMBER
 PRICE                                              NUMBER
 HEALTH                                             NUMBER
 ATTACK                                             NUMBER
 UPGRADE_PRICE                                      NUMBER
 NAME                                               VARCHAR2(45)

CREATE TABLE PLAYER(player_id NUMBER NOT NULL, player_x NUMBER NOT NULL, player_y NUMBER NOT NULL, 
magic NUMBER NOT NULL, attack NUMBER NOT NULL, level_at NUMBER NOT NULL, 
CONSTRAINT pk_game_id PRIMARY KEY(player_id));
             

DESC PLAYER;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------

 PLAYER_ID                                          NUMBER
 PLAYER_X                                           NUMBER
 PLAYER_Y                                           NUMBER
 MAGIC                                              NUMBER
 ATTACK                                             NUMBER
 LEVEL_AT                                           NUMBER

 
CREATE TABLE AVATAR(image BLOB NOT NULL, CONSTRAINT pk_game_id PRIMARY KEY(image));

DESC AVATAR;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------

 IMAGE                                              BLOB

CREATE TABLE PROFILE(profile_id NUMBER NOT NULL, first_name VARCHAR2(45) NOT NULL, last_name
VARCHAR2(45) NOT NULL, email VARCHAR2(45) NOT NULL, password VARCHAR2(45) NOT NULL,
dob DATE NOT NULL,CONSTRAINT pk_profile_id PRIMARY KEY(profile_id), ,CONSTRAINT fk_avatar_image FOREIGN KEY(avatar_image) REFERENCES AVATAR(image),CONSTRAINT fk_game_id FOREIGN KEY(game_id) REFERENCES GAME(game_id));

DESC PROFILE;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------

 PROFILE_ID                                         NUMBER
 FIRST_NAME                                         VARCHAR2(45)
 LAST_NAME                                          VARCHAR2(45)
 EMAIL                                              VARCHAR2(45)
 PASSWORD                                           VARCHAR2(45)
 DOB                                                DATE

CREATE TABLE GAME(game_id NUMBER NOT NULL, stored_magic NUMBER NOT NULL, 
stored_gold NUMBER NOT NULL, CONSTRAINT pk_game_id PRIMARY KEY(game_id),CONSTRAINT fk_player_id FOREIGN KEY(player_id) REFERENCES PLAYER(player_id));

DESC GAME;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------

 GAME_ID                                            NUMBER
 STORED_MAGIC                                       NUMBER
 STORED_GOLD                                        NUMBER
 
 