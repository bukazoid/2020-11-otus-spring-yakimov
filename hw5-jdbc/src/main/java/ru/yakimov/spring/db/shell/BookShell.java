package ru.yakimov.spring.db.shell;

import java.util.List;

import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import lombok.RequiredArgsConstructor;
import ru.yakimov.spring.db.domain.Book;
import ru.yakimov.spring.db.service.AuthorService;
import ru.yakimov.spring.db.service.BookService;
import ru.yakimov.spring.db.service.GenreService;

@ShellCommandGroup("book")
@ShellComponent
@RequiredArgsConstructor
public class BookShell {

	private final BookService bookService;
	private final AuthorService authorService;
	private final GenreService genreService;

	@ShellMethod(value = "List of books", key = { "bl", "blist", "books", "bookList" })
	public String authorList() {
		List<Book> authors = bookService.readAll();

		return authors.stream().map(this::toLine).reduce((a1, a2) -> a1 + "\n" + a2).orElse("");
	}

	@ShellMethod(value = "Add book", key = { "ba", "badd", "bookAdd" })
	public String bookAdd(@ShellOption String name, @ShellOption("id of author who wrote the book") Long author,
			@ShellOption("book's genre") Long genre) {

		Book book2create = new Book(null, name, authorService.read(author), genreService.read(genre));

		Book result = bookService.create(book2create);
		return "created book: \n" + toLine(result);
	}

	@ShellMethod(value = "Read book", key = { "br", "bread", "book", "bookRead" })
	public String authorAdd(@ShellOption Long id) {
		Book result = bookService.read(id);
		return "read book: \n" + toLine(result);
	}

	private String toLine(Book book) {
		return "\t" + book.getId() + "\t" + book.getTitle() + "\t" + book.getAuthor().getName() + "\t"
				+ book.getGenre().getName();
	}
}
