package ru.yakimov.spring.mvc.rest;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.yakimov.spring.mvc.domain.Book;
import ru.yakimov.spring.mvc.service.BookService;

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

	@PutMapping("/api/books/{id}")
	public Book put(@PathVariable Long id, @RequestBody Book book) {
		return mng.update(book);
	}

	@PostMapping("/api/books")
	public Book put(@RequestBody Book book) {
		return mng.create(book);
	}

//
//	@RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
//	public PersonDto get(@PathVariable("id") int id) {
//		Person person = repository.findById(id).orElseThrow(NotFoundException::new);
//		return PersonDto.toDto(person);
//	}
//
//	@RequestMapping(value = "/person", method = RequestMethod.POST)
//	public @ResponseBody PersonDto create(@RequestBody PersonDto dto) {
//		Person account = PersonDto.toDomainObject(dto);
//		Person accountWithId = repository.save(account);
//		return PersonDto.toDto(accountWithId);
//	}
//
//	@DeleteMapping("/person/{id}")
//	public void delete(@PathVariable("id") int id) {
//		repository.deleteById(id);
//	}
//
//	@PutMapping("/person/{id}/holder")
//	public void changeName(@PathVariable("id") int id, @RequestParam("name") String name) {
//		Person person = repository.findById(id).orElseThrow(NotFoundException::new);
//		person.setName(name);
//		repository.save(person);
//	}
//
//	@ExceptionHandler(NotFoundException.class)
//	public ResponseEntity<String> handleNotFound(NotFoundException ex) {
//		return ResponseEntity.badRequest().body("Not found");
//	}
}
