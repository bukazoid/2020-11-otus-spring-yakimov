package ru.yakimov.spring.mvc.rest;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.yakimov.spring.mvc.domain.Author;
import ru.yakimov.spring.mvc.service.AuthorService;

@RestController
public class AuthorController {
	private final AuthorService mng;

	public AuthorController(AuthorService mng) {
		this.mng = mng;
	}

	@RequestMapping(value = "/api/authors", method = RequestMethod.GET)
	public List<Author> get() {
		return mng.readAll();
	}

	@RequestMapping(value = "/api/authors/{id}", method = RequestMethod.GET)
	public Author get(long id) {
		return mng.read(id);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/api/authors/{id}")
	public Author put(@PathVariable Long id, @RequestBody Author author) {
		return mng.update(author);
	}

	@PostMapping("/api/authors")
	public Author put(@RequestBody Author author) {
		return mng.create(author);
	}

}
