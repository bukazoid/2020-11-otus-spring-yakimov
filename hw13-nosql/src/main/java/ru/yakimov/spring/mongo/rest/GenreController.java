package ru.yakimov.spring.mongo.rest;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.yakimov.spring.mongo.domain.Book;
import ru.yakimov.spring.mongo.domain.Genre;
import ru.yakimov.spring.mongo.service.GenreService;

@RestController
public class GenreController {
	private final GenreService mng;

	public GenreController(GenreService mng) {
		this.mng = mng;
	}

	@RequestMapping(value = "/api/genres", method = RequestMethod.GET)
	public List<Genre> get() {
		return mng.readAll();
	}

	@RequestMapping(value = "/api/genres/{id}", method = RequestMethod.GET)
	public Genre get(String id) {
		return mng.read(id);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/api/genres/{id}")
	public Genre put(@PathVariable Long id, @RequestBody Genre genre) {
		return mng.update(genre);
	}

	@PostMapping("/api/genres")
	public Genre put(@RequestBody Genre genre) {
		return mng.create(genre);
	}

}
