package ru.yakimov.spring.db.shell;

import java.util.Arrays;
import java.util.List;

import org.jline.utils.Log;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import lombok.RequiredArgsConstructor;
import ru.yakimov.spring.db.domain.Book;
import ru.yakimov.spring.db.service.AuthorService;
import ru.yakimov.spring.db.service.BookService;
import ru.yakimov.spring.db.service.GenreService;
import ru.yakimov.spring.db.service.ObjectToStringTransformer;

@ShellCommandGroup("book")
@ShellComponent
@RequiredArgsConstructor
public class BookShell {

	private final BookService bookService;
	private final AuthorService authorService;
	private final GenreService genreService;
	private final ObjectToStringTransformer transfromer;

	@ShellMethod(value = "List of books", key = { "bl", "blist", "books", "bookList" })
	public String bookList() {
		List<Book> books = bookService.readAll();
		Log.info("books: {}", books);
		return transfromer.booksToLine(books);
	}

	@ShellMethod(value = "Add book", key = { "ba", "badd", "bookAdd" })
	public String bookAdd(@ShellOption String title, @ShellOption("id of author who wrote the book") Long author,
			@ShellOption("book's genre") Long genre) {

		Book book2create = new Book(title, authorService.read(author), Arrays.asList(genreService.read(genre)));
		Book result = bookService.create(book2create);

		return "created book: \n" + transfromer.toLine(result);
	}

	@ShellMethod(value = "Read book", key = { "br", "bread", "book", "bookRead" })
	public String bookRead(@ShellOption Long id) {
		Book result = bookService.read(id);
		return "read book: \n" + transfromer.toLine(result);
	}

	@ShellMethod(value = "Delete book", key = { "bd", "bdell", "bookDelete" })
	public String bookdelete(@ShellOption Long id) {
		bookService.delete(id);
		return "deleted book: " + id;
	}

}
