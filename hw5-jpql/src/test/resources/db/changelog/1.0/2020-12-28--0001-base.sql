--liquibase formatted sql

--changeset yakimov:2020-12-28--0001-base
create table authors
(
   author_id integer auto_increment,
   name varchar,
   primary key (author_id)
);
create table genres
(
   genre_id integer auto_increment,
   name varchar,
   primary key (genre_id)
);
create table books
(
   book_id integer auto_increment,
   title varchar,
   author_id integer,
   genre_id integer,
   primary key (book_id)
);