--liquibase formatted sql

--changeset yakimov:2020-12-28--0001-base
CREATE TABLE authors
(
   author_id BIGINT auto_increment,
   name VARCHAR,
   PRIMARY KEY (author_id)
);

CREATE TABLE genres
(
   genre_id BIGINT auto_increment,
   name VARCHAR,
   PRIMARY KEY (genre_id)
);

CREATE TABLE books
(
   book_id BIGINT auto_increment,
   title VARCHAR,
   author_id BIGINT,
   PRIMARY KEY (book_id),
   FOREIGN KEY (author_id) REFERENCES authors(author_id)
);

CREATE TABLE book_genre
(
	book_id BIGINT,
	genre_id BIGINT,
	FOREIGN KEY (book_id) REFERENCES books(book_id),
   	FOREIGN KEY (genre_id) REFERENCES genres(genre_id)
);

