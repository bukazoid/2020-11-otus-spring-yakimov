--liquibase formatted sql
--changeset yakimov:2020-12-28--0001-data-genres
insert into books (book_id, title, author_id, genre_id)
values (1, 'Illiad', 1, 5),
       (2, 'Hamlet', 2, 1);