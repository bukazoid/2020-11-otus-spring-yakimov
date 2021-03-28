package ru.yakimov.spring.mvc.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ru.yakimov.spring.mvc.domain.Book;
import ru.yakimov.spring.mvc.service.BookService;

@Slf4j
@RestController
public class BookController {
	private final BookService mng;

	public BookController(BookService mng) {
		this.mng = mng;
	}

	@RequestMapping(value = "/api/books", method = RequestMethod.GET)
	public List<Book> get() {
		return mng.readAll();
	}

	@RequestMapping(value = "/api/book/{id}", method = RequestMethod.GET)
	public Book get(long id) {
		return mng.read(id);
	}

	@DeleteMapping("/api/books/{id}")
	public void delete(@PathVariable Long id) {
		mng.delete(id);
	}

	/**
	 * it can be done without id, it would be not so pretty
	 */
	@PutMapping("/api/books/{id}")
	public Book put(@PathVariable Long id, @RequestBody Book book) {
		log.info("update book: {}", book);
		return mng.update(book);
	}

	@PostMapping("/api/books")
	public Book put(@RequestBody Book book) {
		log.info("create book: {}", book);
		return mng.create(book);
	}

}
