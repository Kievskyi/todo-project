create table if not exists task
(
    id          int auto_increment
        primary key,
    description varchar(100) not null,
    status      int          not null
);

INSERT INTO task (id, description, status) VALUES (1, 'aaa', 1);
INSERT INTO task (id, description, status) VALUES (2, 'bbb', 2);
INSERT INTO task (id, description, status) VALUES (3, 'ccc', 0);
INSERT INTO task (id, description, status) VALUES (4, 'ddd', 1);
INSERT INTO task (id, description, status) VALUES (5, 'eeeds', 2);
INSERT INTO task (id, description, status) VALUES (6, 'fff', 0);
INSERT INTO task (id, description, status) VALUES (7, 'ggg', 1);
INSERT INTO task (id, description, status) VALUES (8, 'hhh', 2);
INSERT INTO task (id, description, status) VALUES (9, 'jjj', 0);
INSERT INTO task (id, description, status) VALUES (10, 'kkk', 1);
INSERT INTO task (id, description, status) VALUES (11, 'lll', 2);
INSERT INTO task (id, description, status) VALUES (12, 'mmm', 0);
INSERT INTO task (id, description, status) VALUES (13, 'nnn', 1);
INSERT INTO task (id, description, status) VALUES (14, 'ooo', 2);
INSERT INTO task (id, description, status) VALUES (15, 'ppp', 0);
INSERT INTO task (id, description, status) VALUES (26, 'aaa', 0);
INSERT INTO task (id, description, status) VALUES (27, 'Hello', 0);
INSERT INTO task (id, description, status) VALUES (28, 'aaa', 0);
INSERT INTO task (id, description, status) VALUES (29, 'BUBUBU', 0);
INSERT INTO task (id, description, status) VALUES (30, 'Hello', 1);
INSERT INTO task (id, description, status) VALUES (31, 'adsf', 0);
INSERT INTO task (id, description, status) VALUES (32, 'adsf', 0);
