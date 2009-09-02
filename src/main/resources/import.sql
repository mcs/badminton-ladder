-- create the default ladder
INSERT INTO Ladder (name) VALUES ('Liga #1');

-- create default roles and rights
INSERT INTO Role (id, name) VALUES (1, 'None');
INSERT INTO Role (id, name) VALUES (2, 'User');
INSERT INTO Role (id, name) VALUES (3, 'Administrator');
INSERT INTO Role (id, name) VALUES (4, 'Superadministrator');

INSERT INTO Right (id, name) VALUES (1, 'view ladder');
INSERT INTO Right (id, name) VALUES (2, 'create challenge');
INSERT INTO Right (id, name) VALUES (3, 'enter result');
INSERT INTO Right (id, name) VALUES (4, 'add player to ladder');
INSERT INTO Right (id, name) VALUES (5, 'see activity log');

INSERT INTO Role_right (Role_id, rights_id) VALUES (1, 1);
INSERT INTO Role_right (Role_id, rights_id) VALUES (2, 1);
INSERT INTO Role_right (Role_id, rights_id) VALUES (3, 1);
INSERT INTO Role_right (Role_id, rights_id) VALUES (4, 1);

INSERT INTO Role_right (Role_id, rights_id) VALUES (2, 2);
INSERT INTO Role_right (Role_id, rights_id) VALUES (3, 2);
INSERT INTO Role_right (Role_id, rights_id) VALUES (4, 2);

INSERT INTO Role_right (Role_id, rights_id) VALUES (2, 3);
INSERT INTO Role_right (Role_id, rights_id) VALUES (3, 3);
INSERT INTO Role_right (Role_id, rights_id) VALUES (4, 3);

INSERT INTO Role_right (Role_id, rights_id) VALUES (3, 4);
INSERT INTO Role_right (Role_id, rights_id) VALUES (4, 4);

INSERT INTO Role_right (Role_id, rights_id) VALUES (4, 5);

-- create default admin user
INSERT INTO User (login, password, role_id) VALUES ('admin', 'adminadmin', 3);

-- create default players
INSERT INTO Player(name, ladder_id, player_rank) VALUES ('Spieler 1', 1, 0);
INSERT INTO Player(name, ladder_id, player_rank) VALUES ('Spieler 2', 1, 1);
INSERT INTO Player(name, ladder_id, player_rank) VALUES ('Spieler 3', 1, 2);
INSERT INTO Player(name, ladder_id, player_rank) VALUES ('Spieler 4', 1, 3);
INSERT INTO Player(name, ladder_id, player_rank) VALUES ('Spieler 5', 1, 4);
INSERT INTO Player(name, ladder_id, player_rank) VALUES ('Spieler 6', 1, 5);