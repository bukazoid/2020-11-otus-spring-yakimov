--liquibase formatted sql
--changeset yakimov:2020-12-28--0001-data-authors
insert into authors (author_id, name)
values (1, 'buka'),
       (2, 'pavel'),
       (3, 'yakimov'),
       (4, 'unknown');