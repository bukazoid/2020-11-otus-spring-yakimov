package ru.yakimov.spring.mvc.service;

import java.util.List;

import ru.yakimov.spring.mvc.domain.Author;
import ru.yakimov.spring.mvc.domain.Book;
import ru.yakimov.spring.mvc.domain.BookComment;
import ru.yakimov.spring.mvc.domain.Genre;

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
