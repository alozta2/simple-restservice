CREATE TABLE IF NOT EXISTS todo (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(50),
description VARCHAR(50),
status TINYINT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS task (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(50),
description VARCHAR(50),
todo_id INT NOT NULL,
FOREIGN KEY (todo_id) REFERENCES todo(id)
);

INSERT INTO todo (name, description, status) VALUES ('invisible todo.', 'deleted todos will be connected to this todo.', 7);
INSERT INTO todo (name, description) VALUES ('first todo', 'this the first todo.');
INSERT INTO todo (name) VALUES ('second todo');
INSERT INTO todo (name) VALUES ('this is the third todo');

INSERT INTO task (name, description, todo_id) VALUES ('first todo name', 'this the first todo description.', 1);
INSERT INTO task (todo_id) VALUES (1);
INSERT INTO task (name, todo_id) VALUES ('second todo name', 2);
