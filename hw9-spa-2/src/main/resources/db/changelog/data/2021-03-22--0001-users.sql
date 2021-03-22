--liquibase formatted sql
--changeset yakimov:2021-02-22--0001-data-users
insert into users (user_id, login, password)
values (1, 'admin','admin'),
       (2, 'user','user'),
       (3, 'user2', 'user2');