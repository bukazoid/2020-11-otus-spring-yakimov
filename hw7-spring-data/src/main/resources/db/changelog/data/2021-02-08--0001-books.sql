--liquibase formatted sql
--changeset yakimov:2020-12-28--0001-data-genres
values (1, 'Illiad', 1),
       (2, 'Hamlet', 2);
       
INSERT INTO book_genre (book_id, genre_id)
VALUES (1,5),
	   (1,1),
	   (2,1);