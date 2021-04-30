package ru.yakimov.spring.mongo.service;

import java.util.List;

import ru.yakimov.spring.mongo.domain.Author;
import ru.yakimov.spring.mongo.domain.Book;
import ru.yakimov.spring.mongo.domain.BookComment;
import ru.yakimov.spring.mongo.domain.Genre;

public interface ObjectToStringTransformer {
	String toLine(Author author);

	String toLine(Genre genre);

	String toLine(Book book);

	String toLine(BookComment comment);

	String authorsToLine(List<Author> authors);

	String genresToLine(List<Genre> genres);

	String booksToLine(List<Book> books);

	String commentsToLine(List<BookComment> comments);

}
