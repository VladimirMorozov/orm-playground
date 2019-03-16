INSERT INTO company (name) VALUES ('Garden Gnome');
INSERT INTO company (name) VALUES ('Socks Ltd.');
INSERT INTO company (name) VALUES ('Cute Animals');

INSERT INTO department (company_id, name) VALUES (1, 'Green');
INSERT INTO department (company_id, name) VALUES (1, 'Red');
INSERT INTO department (company_id, name) VALUES (2, 'Cotton');
INSERT INTO department (company_id, name) VALUES (2, 'Bamboo');
INSERT INTO department (company_id, name) VALUES (3, 'Empty');
INSERT INTO department (company_id, name) VALUES (3, 'Kittens');

INSERT INTO employee (department_id, name, position) VALUES (1, 'Vladimir', 'programmer');
INSERT INTO employee (department_id, name, position) VALUES (1, 'Jeff', 'janitor');
INSERT INTO employee (department_id, name, position) VALUES (1, 'Dora', 'programmer');
INSERT INTO employee (department_id, name, position) VALUES (2, 'James', 'programmer');
INSERT INTO employee (department_id, name, position) VALUES (2, 'Linda', 'manager');
INSERT INTO employee (department_id, name, position) VALUES (3, 'Gregory', 'dreamer');
INSERT INTO employee (department_id, name, position) VALUES (3, 'Harry', 'manager');
INSERT INTO employee (department_id, name, position) VALUES (3, 'Lara', 'janitor');
INSERT INTO employee (department_id, name, position) VALUES (4, 'Tom', null);
INSERT INTO employee (department_id, name, position) VALUES (6, 'Drake', 'accountant');

UPDATE department SET head_id = 1 WHERE id = 1;
UPDATE department SET head_id = 5 WHERE id = 2;
UPDATE department SET head_id = 7 WHERE id = 3;
UPDATE department SET head_id = 10 WHERE id = 6;