DROP DATABASE
IF EXISTS sbb;

CREATE DATABASE sbb;

USE sbb;

CREATE TABLE Question (
    id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `subject` VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    create_date DATETIME NOT NULL,
    modify_date DATETIME NOT NULL,
    Answer_id INT(11) UNSIGNED,
    question_voter_id INT(11) UNSIGNED,
    author_id INT(11) UNSIGNED NOT NULL
);

INSERT INTO Question SET
create_date = NOW(),
modify_date = NOW(),
`subject` = '질문 1',
content = '질문내용 1',
Answer_id = 1,
author_id = 1;

INSERT INTO Question SET
create_date = NOW(),
modify_date = NOW(),
`subject` = '질문 2',
content = '질문내용 2',
Answer_id = 2,
author_id = 2;

INSERT INTO Question SET
create_date = NOW(),
modify_date = NOW(),
`subject` = '질문 3',
content = '질문내용 3',
Answer_id = 3,
author_id = 3;


CREATE TABLE Answer (
    id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    content TEXT NOT NULL,
    create_date DATETIME NOT NULL,
    modify_date DATETIME NOT NULL,
    question_id INT(11) UNSIGNED NOT NULL,
    answer_voter_id INT(11) UNSIGNED,
    author_id INT(11) UNSIGNED NOT NULL
);

INSERT INTO Answer SET
create_date = NOW(),
modify_date = NOW(),
content = '답변내용 1',
question_id = 1,
author_id = 1;

INSERT INTO Answer SET
create_date = NOW(),
modify_date = NOW(),
content = '답변내용 2',
question_id = 2,
author_id = 2;

INSERT INTO Answer SET
create_date = NOW(),
modify_date = NOW(),
content = '답변내용 3',
question_id = 3,
author_id = 3;

CREATE TABLE site_user(
    id INT(20) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(30) NOT NULL UNIQUE,
    `password` VARCHAR(150) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO site_user SET
username = "유저1",
`password` = "1234",
email = "test1@test.com";

INSERT INTO site_user SET
username = "유저2",
`password` = "1234",
email = "test2@test.com";

INSERT INTO site_user SET
username = "유저3",
`password` = "1234",
email = "test3@test.com";

CREATE TABLE answer_voter(
    answer_id INT(11) UNSIGNED NOT NULL,
    voter_id INT(11) UNSIGNED NOT NULL PRIMARY KEY

);

CREATE TABLE question_voter(
    question_id INT(11) UNSIGNED NOT NULL,
    voter_id INT(11) UNSIGNED NOT NULL PRIMARY KEY
);

SELECT * FROM Question;
SELECT * FROM Answer;
SELECT * FROM site_user;
SELECT * FROM question_voter;