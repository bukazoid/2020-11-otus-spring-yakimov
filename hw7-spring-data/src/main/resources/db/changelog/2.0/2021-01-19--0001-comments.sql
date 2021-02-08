--liquibase formatted sql

--changeset yakimov:2021-01-19--0001-comments
CREATE TABLE comments
(
   comment_id BIGINT auto_increment,
   text VARCHAR,
   book_id BIGINT,
   PRIMARY KEY (comment_id),
   FOREIGN KEY (book_id) REFERENCES books(book_id) ON DELETE CASCADE 
);