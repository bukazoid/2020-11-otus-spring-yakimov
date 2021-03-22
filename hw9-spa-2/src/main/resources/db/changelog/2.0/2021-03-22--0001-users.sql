--liquibase formatted sql

--changeset yakimov:2021-03-22--0001-uses
CREATE TABLE users
(
   user_id BIGINT auto_increment,
   login VARCHAR(16),
   password VARCHAR(16),
   PRIMARY KEY (user_id) 
);