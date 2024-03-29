package ru.yakimov.spring.mvc.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ru.yakimov.spring.mvc.domain.Author;
import ru.yakimov.spring.mvc.domain.Book;
import ru.yakimov.spring.mvc.domain.BookComment;
import ru.yakimov.spring.mvc.domain.Genre;

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
		String genreList = book.getGenres().stream().map(Genre::getName).collect(Collectors.joining(","));
		return "\t" + book.getId() + "\t" + book.getTitle() + "\t" + book.getAuthor().getName() + "\t" + genreList;
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

	@Override
	public String toLine(BookComment comment) {
		return "\t" + comment.getId() + ": " + comment.getText();
	}

	@Override
	public String commentsToLine(List<BookComment> comments) {
		return comments.stream().map(this::toLine).collect(Collectors.joining("\n"));
	}

}
