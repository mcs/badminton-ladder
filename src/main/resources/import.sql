-- create the default ladder
INSERT INTO Ladder (name) VALUES ('Liga #1');

-- create roles
INSERT INTO Role (id, name) VALUES (1, 'Administrator');
INSERT INTO Role (id, name) VALUES (2, 'User');

-- create default admin user
INSERT INTO User (login, password, role_id) VALUES ('admin', 'adminadmin', 1);