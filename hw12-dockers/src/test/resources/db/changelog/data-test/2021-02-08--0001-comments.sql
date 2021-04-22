--liquibase formatted sql
--changeset yakimov:2021-01-09--0001-data-genres
INSERT INTO comments (comment_id, text, book_id) 
VALUES (1, 'Boooring', 1),
	   (2, 'Acient', 1),
	   (3, 'nice!', 1),
	   (4, 'too long', 1),
	   (5, 'Cool', 2),
	   (6, 'I prefer movie', 2),
	   (7, 'Too many words', 2)
