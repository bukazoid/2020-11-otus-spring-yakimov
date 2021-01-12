package ru.yakimov.spring.db.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ru.yakimov.spring.db.domain.Author;
import ru.yakimov.spring.db.domain.Book;
import ru.yakimov.spring.db.domain.Genre;

@Service
public class ObjectToStringTransformerImpl implements ObjectToStringTransformer {

	@Override
	public String toLine(Author author) {
		return "\t" + author.getId() + "\t" + author.getName();
	}

	@Override
	public String toLine(Genre genre) {
		return "\t" + genre.getId() + "\t" + genre.getName();
	}

	@Override
	public String toLine(Book book) {
		return "\t" + book.getId() + "\t" + book.getTitle() + "\t" + book.getAuthor().getName() + "\t"
				+ book.getGenre().getName();
	}

	@Override
	public String authorsToLine(List<Author> authors) {
		return authors.stream().map(this::toLine).collect(Collectors.joining("\n"));
	}

	@Override
	public String genresToLine(List<Genre> genres) {
		return genres.stream().map(this::toLine).collect(Collectors.joining("\n"));
	}

	@Override
	public String booksToLine(List<Book> books) {
		return books.stream().map(this::toLine).collect(Collectors.joining("\n"));
	}

}
