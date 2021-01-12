package ru.yakimov.spring.db.shell;

import java.util.List;

import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import lombok.RequiredArgsConstructor;
import ru.yakimov.spring.db.domain.Author;
import ru.yakimov.spring.db.service.AuthorService;
import ru.yakimov.spring.db.service.ObjectToStringTransformer;

@ShellCommandGroup("author")
@ShellComponent
@RequiredArgsConstructor
public class AuthorShell {

	private final AuthorService authorService;
	private final ObjectToStringTransformer transfromer;

	@ShellMethod(value = "List of authors", key = { "al", "alist", "authors", "authorList" })
	public String authorList() {
		List<Author> authors = authorService.readAll();
		return transfromer.authorsToLine(authors);
	}

	@ShellMethod(value = "Add author", key = { "aa", "aadd", "authorAdd" })
	public String authorAdd(@ShellOption String name) {
		Author result = authorService.create(new Author(null, name));
		return "created author: \n" + transfromer.toLine(result);
	}

	@ShellMethod(value = "Read author", key = { "ar", "aread", "author", "authorRead" })
	public String authorRead(@ShellOption Long id) {
		Author result = authorService.read(id);
		return "read author: \n" + transfromer.toLine(result);
	}

}
