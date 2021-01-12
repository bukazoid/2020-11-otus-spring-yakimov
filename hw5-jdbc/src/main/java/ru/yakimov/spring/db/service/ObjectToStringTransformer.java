package ru.yakimov.spring.db.service;

import java.util.List;

import ru.yakimov.spring.db.domain.Author;
import ru.yakimov.spring.db.domain.Book;
import ru.yakimov.spring.db.domain.Genre;

public interface ObjectToStringTransformer {
	String toLine(Author author);

	String toLine(Genre genre);

	String toLine(Book book);

	String authorsToLine(List<Author> authors);

	String genresToLine(List<Genre> genres);

	String booksToLine(List<Book> books);

}
