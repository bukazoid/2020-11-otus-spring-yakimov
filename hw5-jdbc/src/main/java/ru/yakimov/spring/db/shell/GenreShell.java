package ru.yakimov.spring.db.shell;

import java.util.List;

import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import lombok.RequiredArgsConstructor;
import ru.yakimov.spring.db.domain.Genre;
import ru.yakimov.spring.db.service.GenreService;

@ShellCommandGroup("genre")
@ShellComponent
@RequiredArgsConstructor
public class GenreShell {

	private final GenreService genreService;

	@ShellMethod(value = "List of genres", key = { "gl", "glist", "genres", "genreList" })
	public String genreList() {
		List<Genre> genres = genreService.readAll();

		return genres.stream().map(this::toLine).reduce((a1, a2) -> a1 + "\n" + a2).orElse("");
	}

	@ShellMethod(value = "Add genre", key = { "ga", "gadd", "genreAdd" })
	public String genreAdd(@ShellOption String name) {
		Genre result = genreService.create(new Genre(null, name));
		return "created genre: \n" + toLine(result);
	}

	@ShellMethod(value = "Read genre", key = { "gr", "gread", "genre", "genreRead" })
	public String genreAdd(@ShellOption Long id) {
		Genre result = genreService.read(id);
		return "read genre: \n" + toLine(result);
	}

	private String toLine(Genre genre) {
		return "\t" + genre.getId() + "\t" + genre.getName();
	}
}
