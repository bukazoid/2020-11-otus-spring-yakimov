--liquibase formatted sql
--changeset yakimov:2021-01-09--0001-data-genres
insert into books (book_id, title, author_id)
values (1, 'Illiad', 1),
       (2, 'Hamlet', 2);
       
INSERT INTO book_genre (book_id, genre_id)
VALUES (1,5),
	   (1,1),
	   (2,1);