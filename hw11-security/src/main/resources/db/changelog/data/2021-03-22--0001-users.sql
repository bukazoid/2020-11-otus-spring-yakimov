--liquibase formatted sql
--changeset yakimov:2021-02-22--0001-data-users
insert into users (user_id, login, password, role)
values (1, 'admin','admin','ADMIN'),
       (2, 'user','user','USER'),
       (3, 'user2', 'user2','USER');
--yep, it should be way to set several roles per user