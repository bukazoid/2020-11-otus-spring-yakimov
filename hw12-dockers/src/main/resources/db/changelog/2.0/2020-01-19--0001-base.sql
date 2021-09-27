--liquibase formatted sql

--changeset yakimov:2020-12-28--0001-base
CREATE TABLE authors
(
   author_id BIGSERIAL,
   name VARCHAR,
   PRIMARY KEY (author_id)
);

CREATE TABLE genres
(
   genre_id BIGSERIAL,
   name VARCHAR,
   PRIMARY KEY (genre_id)
);

CREATE TABLE books
(
   book_id BIGSERIAL,
   title VARCHAR,
   author_id BIGINT,
   PRIMARY KEY (book_id),
   FOREIGN KEY (author_id) REFERENCES authors(author_id)
);

CREATE TABLE book_genre
(
	book_id BIGINT,
	genre_id BIGINT,
	FOREIGN KEY (book_id) REFERENCES books(book_id) ON DELETE CASCADE ,
   	FOREIGN KEY (genre_id) REFERENCES genres(genre_id)ON DELETE CASCADE 
);

