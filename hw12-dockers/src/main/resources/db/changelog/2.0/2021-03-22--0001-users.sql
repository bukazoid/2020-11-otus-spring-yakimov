--liquibase formatted sql

--changeset yakimov:2021-03-22--0001-users
CREATE TABLE users
(
   user_id BIGSERIAL,
   login VARCHAR(16),
   password VARCHAR(16),
   role VARCHAR(16),
   PRIMARY KEY (user_id) 
);