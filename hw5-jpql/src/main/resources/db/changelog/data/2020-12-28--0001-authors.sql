--liquibase formatted sql
--changeset yakimov:2020-12-28--0001-data-authors
insert into authors (author_id, name)
values (1, 'Homer'),
       (2, 'Shakespeare'),
       (3, 'Lermontov');