--liquibase formatted sql
--changeset yakimov:2020-12-28--0001-data-genres
insert into genres (genre_id, name)
values (1, 'Drama'),
       (2, 'Science Fiction'),
       (3, 'Fantasy'),
       (4, 'Detective'),
       (5, 'History');